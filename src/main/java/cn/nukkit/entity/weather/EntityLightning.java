package cn.nukkit.entity.weather;

import cn.nukkit.Server;
import cn.nukkit.api.PowerNukkitDifference;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.*;
import cn.nukkit.blockproperty.value.OxidizationLevel;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.block.BlockFadeEvent;
import cn.nukkit.event.block.BlockIgniteEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.LightningDistractEvent;
import cn.nukkit.level.GameRule;
import cn.nukkit.level.Position;
import cn.nukkit.level.Sound;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.level.particle.ElectricSparkParticle;
import cn.nukkit.level.vibration.VibrationEvent;
import cn.nukkit.level.vibration.VibrationType;
import cn.nukkit.math.AxisAlignedBB;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

/**
 * @author boybook
 * @since 2016/2/27
 */
public class EntityLightning extends Entity implements EntityLightningStrike {

    public static final int NETWORK_ID = 93;
    public int state;
    public int liveTime;
    protected boolean isEffect = true;


    public EntityLightning(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    private static boolean isVulnerableOxidizable(@NotNull Block block) {
        return block instanceof Oxidizable && (!(block instanceof Waxable) || !((Waxable) block).isWaxed());
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    @Override
    protected void initEntity() {
        super.initEntity();

        if(!(this.getLevelBlock() instanceof BlockLightningRod) && !(this.getLevelBlock().down() instanceof BlockLightningRod)) {
            x:
            for(int x = -32; x <= 32; x++) {
                for(int y = -32; y <= 32; y++) {
                    for(int z = -32; z <= 32; z++) {
                        Block rod = this.getLevel().getBlock(this.getFloorX() + x, this.getFloorY() + y, this.getFloorZ() + z);
                        if(rod instanceof BlockLightningRod) {
                            LightningDistractEvent lightningDistractEvent = new LightningDistractEvent(this, rod);
                            Server.getInstance().getPluginManager().callEvent(lightningDistractEvent);
                            if(!lightningDistractEvent.isCancelled()) {
                                this.setPosition(lightningDistractEvent.getDistractionBlock());
                            }
                            break x;
                        }
                    }
                }
            }
        }

        this.setHealth(4);
        this.setMaxHealth(4);

        this.state = 2;
        this.liveTime = ThreadLocalRandom.current().nextInt(3) + 1;

        if (isEffect && this.level.gameRules.getBoolean(GameRule.DO_FIRE_TICK) && (this.server.getDifficulty() >= 2)) {
            Block block = this.getLevelBlock();
            if (block.getId() == 0 || block.getId() == Block.TALL_GRASS) {
                BlockFire fire = (BlockFire) Block.get(BlockID.FIRE);
                fire.x = block.x;
                fire.y = block.y;
                fire.z = block.z;
                fire.level = level;
//                this.getLevel().setBlock(fire, fire, true); WTF???
                if (fire.isBlockTopFacingSurfaceSolid(fire.down()) || fire.canNeighborBurn()) {

                    BlockIgniteEvent e = new BlockIgniteEvent(block, null, this, BlockIgniteEvent.BlockIgniteCause.LIGHTNING);
                    Server.getInstance().getPluginManager().callEvent(e);

                    if (!e.isCancelled()) {
                        level.setBlock(fire, fire, true);
                        level.scheduleUpdate(fire, fire.tickRate() + ThreadLocalRandom.current().nextInt(10));
                    }
                }
            }
        }
    }

    @Override
    public boolean isEffect() {
        return this.isEffect;
    }

    @Override
    public void setEffect(boolean e) {
        this.isEffect = e;
    }

    @Override
    public boolean attack(EntityDamageEvent source) {
        //false?
        source.setDamage(0);
        return super.attack(source);
    }

    @PowerNukkitDifference(info = "Using new method to play sounds", since = "1.4.0.0-PN")
    @Override
    public boolean onUpdate(int currentTick) {
        if (this.closed) {
            return false;
        }

        int tickDiff = currentTick - this.lastUpdate;

        if (tickDiff <= 0 && !this.justCreated) {
            return true;
        }

        this.lastUpdate = currentTick;

        this.entityBaseTick(tickDiff);

        if (this.state == 2) {
            this.level.addSound(this, Sound.AMBIENT_WEATHER_THUNDER);
            this.level.addSound(this, Sound.RANDOM_EXPLODE);

            Block down = getLevel().getBlock(down());
            if (isVulnerableOxidizable(down)) {
                Map<Position, OxidizationLevel> changes = new LinkedHashMap<>();
                changes.put(new Position().setComponents(down).setLevel(level), OxidizationLevel.UNAFFECTED);

                ThreadLocalRandom random = ThreadLocalRandom.current();
                int scans = random.nextInt(3) + 3;

                Position directionPos = new Position().setLevel(level);
                Position randomPos = new Position().setLevel(level);
                Supplier<Vector3> cleanOxidizationAround = () -> {
                    for (int attempt = 0; attempt < 10; attempt++) {
                        randomPos.x = directionPos.x + (random.nextInt(3) - 1);
                        randomPos.y = directionPos.y + (random.nextInt(3) - 1);
                        randomPos.z = directionPos.z + (random.nextInt(3) - 1);
                        Block possibility = level.getBlock(randomPos);
                        if (isVulnerableOxidizable(possibility)) {
                            Position nextPos = randomPos.clone();
                            changes.compute(nextPos, (k, v) -> {
                                int nextLevel = v == null ?
                                        ((Oxidizable) possibility).getOxidizationLevel().ordinal() - 1 :
                                        v.ordinal() - 1;
                                return OxidizationLevel.values()[Math.max(0, nextLevel)];
                            });
                            return nextPos;
                        }
                    }
                    return null;
                };

                IntConsumer cleanOxidizationAroundLoop = count -> {
                    directionPos.setComponents(down);
                    for (int i = 0; i < count; ++i) {
                        Vector3 next = cleanOxidizationAround.get();
                        if (next != null) {
                            directionPos.setComponents(next);
                        } else {
                            break;
                        }
                    }
                };

                for (int scan = 0; scan < scans; ++scan) {
                    int count = random.nextInt(8) + 1;
                    cleanOxidizationAroundLoop.accept(count);
                }

                for (Map.Entry<Position, OxidizationLevel> entry : changes.entrySet()) {
                    Block current = level.getBlock(entry.getKey());
                    Block next = ((Oxidizable) current).getStateWithOxidizationLevel(entry.getValue()).getBlock(current);
                    BlockFadeEvent event = new BlockFadeEvent(current, next);
                    Server.getInstance().getPluginManager().callEvent(event);
                    if (event.isCancelled()) {
                        break;
                    }
                    getLevel().setBlock(entry.getKey(), event.getNewState());
                    getLevel().addParticle(new ElectricSparkParticle(entry.getKey()));
                }
            }
        }

        this.state--;

        if (this.state < 0) {
            if (this.liveTime == 0) {
                this.close();
                return false;
            } else if (this.state < -ThreadLocalRandom.current().nextInt(10)) {
                this.liveTime--;
                this.state = 1;

                if (this.isEffect && this.level.gameRules.getBoolean(GameRule.DO_FIRE_TICK)) {
                    Block block = this.getLevelBlock();

                    if (block.getId() == Block.AIR || block.getId() == Block.TALL_GRASS) {
                        BlockIgniteEvent e = new BlockIgniteEvent(block, null, this, BlockIgniteEvent.BlockIgniteCause.LIGHTNING);
                        Server.getInstance().getPluginManager().callEvent(e);

                        if (!e.isCancelled()) {
                            Block fire = Block.get(BlockID.FIRE);
                            this.level.setBlock(block, fire);
                            this.getLevel().scheduleUpdate(fire, fire.tickRate());
                        }
                    }
                }
            }
        }

        if (this.state >= 0) {
            if (this.isEffect) {
                AxisAlignedBB bb = getBoundingBox().grow(3, 3, 3);
                bb.setMaxX(bb.getMaxX() + 6);

                for (Entity entity : this.level.getCollidingEntities(bb, this)) {
                    entity.onStruckByLightning(this);
                }
                Block rod = this.getLevelBlock().down();
                if(rod instanceof BlockLightningRod) {
                    ((BlockLightningRod) rod).onStruckByLightning(this);
                }
            }
        }

        return true;
    }


    @PowerNukkitOnly
    @Since("1.5.1.0-PN")
    @Override
    public String getOriginalName() {
        return "Lightning Bolt";
    }

    @Override
    public void spawnToAll() {
        this.level.getVibrationManager().callVibrationEvent(new VibrationEvent(this, this.clone(), VibrationType.LIGHTNING_STRIKE));
        super.spawnToAll();
    }
}
