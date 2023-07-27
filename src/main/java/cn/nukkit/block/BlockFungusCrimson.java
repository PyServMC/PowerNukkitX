package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.level.generator.object.tree.ObjectCrimsonTree;
import cn.nukkit.math.NukkitRandom;
import cn.nukkit.event.level.StructureGrowEvent;
import cn.nukkit.level.ListChunkManager;
import cn.nukkit.math.NukkitRandom;
import cn.nukkit.utils.BlockColor;

import javax.annotation.Nullable;

@Since("1.4.0.0-PN")
@PowerNukkitOnly
public class BlockFungusCrimson extends BlockFungus {
    private final ObjectCrimsonTree feature = new ObjectCrimsonTree();

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    public BlockFungusCrimson() {
        // Does nothing
    }

    @Override
    public int getId() {
        return CRIMSON_FUNGUS;
    }

    @Override
    public String getName() {
        return "Crimson Fungus";
    }

    @PowerNukkitOnly
    @Override
    protected boolean canGrowOn(Block support) {
        if (support.getId() == CRIMSON_NYLIUM) {
            for (int i = 1; i <= this.feature.getTreeHeight(); i++) {
                if (this.up(i).getId() != 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @PowerNukkitOnly
    @Override
    public boolean grow(@Nullable Player cause) {
        ObjectCrimsonTree crimsonTree = new ObjectCrimsonTree();
        StructureGrowEvent event = new StructureGrowEvent(this, new ListChunkManager(level).getBlocks());
        level.getServer().getPluginManager().callEvent(event);
        if(event.isCancelled()) {
            return false;
        }
        crimsonTree.placeObject(level, getFloorX(), getFloorY(), getFloorZ(), new NukkitRandom());
        return true;
    }

}
