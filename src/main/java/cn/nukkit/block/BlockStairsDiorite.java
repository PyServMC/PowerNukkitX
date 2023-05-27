package cn.nukkit.block;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.item.ItemTool;

@PowerNukkitOnly
public class BlockStairsDiorite extends BlockStairs {
    @PowerNukkitOnly
    public BlockStairsDiorite() {
        this(0);
    }

    @PowerNukkitOnly
    public BlockStairsDiorite(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return DIORITE_STAIRS;
    }

    @Override
    public double getHardness() {
        return 1.5;
    }

    @Override
    public double getResistance() {
        return 30;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    @PowerNukkitOnly
    public int getToolTier() {
        return ItemTool.TIER_WOODEN;
    }

    @Override
    public String getName() {
        return "Diorite Stairs";
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
