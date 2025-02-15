package cn.nukkit.level.generator.populator.impl.nether;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.level.ChunkManager;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.level.generator.populator.type.Populator;
import cn.nukkit.math.NukkitMath;
import cn.nukkit.math.NukkitRandom;

import java.util.ArrayList;

public class PopulatorBasaltDeltaMagma extends Populator {
    private ChunkManager level;

    @Override
    public void populate(ChunkManager level, int chunkX, int chunkZ, NukkitRandom random, FullChunk chunk) {
        this.level = level;
        int amount = random.nextBoundedInt(4) + 20;

        for (int i = 0; i < amount; ++i) {
            int x = NukkitMath.randomRange(random, chunkX << 4, (chunkX << 4) + 15);
            int z = NukkitMath.randomRange(random, chunkZ << 4, (chunkZ << 4) + 15);
            ArrayList<Integer> ys = this.getHighestWorkableBlocks(x, z);
            for (int y : ys) {
                if (y <= 1) continue;
                this.level.setBlockAt(x, y, z, BlockID.MAGMA);
            }
        }
    }

    private ArrayList<Integer> getHighestWorkableBlocks(int x, int z) {
        int y;
        ArrayList<Integer> blockYs = new ArrayList<>();
        for (y = 128; y > 0; --y) {
            int b = this.level.getBlockIdAt(x, y, z);
            int b1 = this.level.getBlockIdAt(x + 1, y, z);
            int b2 = this.level.getBlockIdAt(x - 1, y, z);
            int b3 = this.level.getBlockIdAt(x, y, z + 1);
            int b4 = this.level.getBlockIdAt(x, y, z - 1);
            if ((b == Block.BASALT || b == Block.BLACKSTONE) &&
                    this.level.getBlockIdAt(x, y + 1, z) == 0 && (
                    b1 == BlockID.STILL_LAVA ||
                            b2 == BlockID.STILL_LAVA ||
                            b3 == BlockID.STILL_LAVA ||
                            b4 == BlockID.STILL_LAVA ||
                            b1 == BlockID.FLOWING_LAVA ||
                            b2 == BlockID.FLOWING_LAVA ||
                            b3 == BlockID.FLOWING_LAVA ||
                            b4 == BlockID.FLOWING_LAVA
            )
            ) {
                blockYs.add(y);
            }
        }
        return blockYs;
    }
}
