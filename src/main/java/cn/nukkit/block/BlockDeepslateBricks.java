package cn.nukkit.block;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.item.ItemTool;

/**
 * @author PleaseInsertNameHere
 * @since 28/06/2021
 */
@PowerNukkitOnly
@Since("FUTURE")
public class BlockDeepslateBricks extends BlockSolid {
    @PowerNukkitOnly
    @Since("FUTURE")
    public BlockDeepslateBricks(){
    }

    @Override
    public String getName() {
        return "Deepslate Bricks";
    }

    @Override
    public int getId() {
        return ItemTool.DEEPSLATE_BRICKS;
    }

    @Override
    public double getHardness() {
        return 3.5;
    }

    @Override
    public double getResistance() {
        return 6.0;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public int getToolTier() {
        return ItemTool.TIER_WOODEN;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
