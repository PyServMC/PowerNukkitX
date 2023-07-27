package cn.nukkit.entity;

import cn.nukkit.Player;
import cn.nukkit.api.PowerNukkitDifference;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.entity.item.EntityBalloon;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBalloon;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import org.jetbrains.annotations.NotNull;

/**
 * 实体生物
 *
 * @author MagicDroidX (Nukkit Project)
 */
@PowerNukkitDifference(since = "1.4.0.0-PN", info = "Implements EntityNameable only in PowerNukkit")
public abstract class EntityCreature extends EntityLiving implements EntityNameable, EntityAgeable {
    private EntityBalloon balloon = null;

    public EntityCreature(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    // Armor stands, when implemented, should also check this.
    @Override
    public boolean onInteract(Player player, Item item, Vector3 clickedPos) {
        switch (item.getId()) {
            case Item.NAME_TAG:
                if (!player.isAdventure()) {
                    return applyNameTag(player, item);
                }
                break;
            case Item.BALLOON:
                if (this instanceof EntityBalloonable && item instanceof ItemBalloon) {
                    this.setBalloon(EntityBalloon.create(this.add(0.0D, 1.75D, 0.0D), ((ItemBalloon) item).getDyeColor(), 256.0F, false, this));
                    this.balloon.spawnToAll();
                    return true;
                }
                break;
        }
        return false;
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    @Override
    public final boolean playerApplyNameTag(@NotNull Player player, @NotNull Item item) {
        return applyNameTag(player, item);
    }

    // Structured like this so I can override nametags in player and dragon classes
    // without overriding onInteract.
    protected boolean applyNameTag(Player player, Item item) {
        if (item.hasCustomName()) {
            this.setNameTag(item.getCustomName());
            this.setNameTagVisible(true);

            if (!player.isCreative()) {
                player.getInventory().removeItem(item);
            }
            // Set entity as persistent.
            return true;
        }
        return false;
    }

    public EntityBalloon getBalloon() {
        return this instanceof EntityBalloonable ? balloon : null;
    }

    public void setBalloon(EntityBalloon balloon) {
        if (this instanceof EntityBalloonable) {
            this.balloon = balloon;
        }
    }

    @Override
    public boolean isBaby() {
        return this.getDataFlag(DATA_FLAGS, Entity.DATA_FLAG_BABY);
    }

    @PowerNukkitXOnly
    @Since("1.6.0.0-PNX")
    public void setBaby(boolean flag) {
        this.setDataFlag(DATA_FLAGS, Entity.DATA_FLAG_BABY, flag);
        if (flag)
            this.setScale(this.getScale() / 2);
        else
            this.setScale(this.getScale() * 2);
    }
}
