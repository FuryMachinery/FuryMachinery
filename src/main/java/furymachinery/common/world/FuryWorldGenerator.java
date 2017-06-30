package furymachinery.common.world;

import java.util.Random;

import furymachinery.common.FuryBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class FuryWorldGenerator implements IWorldGenerator {
	public WorldGenerator genAurichalcite, genLislkirchnerite, genPyromorphite, genIlmenite;

	public FuryWorldGenerator() {
		genAurichalcite = new WorldGenMinable(FuryBlocks.aurichalciteOre.getDefaultState(), 9);
		genLislkirchnerite = new WorldGenMinable(FuryBlocks.lislkirchneriteOre.getDefaultState(), 8);
		genPyromorphite = new WorldGenMinable(FuryBlocks.pyromorphiteOre.getDefaultState(), 5);
		genIlmenite = new WorldGenMinable(FuryBlocks.ilmeniteOre.getDefaultState(), 3);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		case 0:
			runGenerator(genAurichalcite, world, random, chunkX, chunkZ, 25, 0, 64);
			runGenerator(genLislkirchnerite, world, random, chunkX, chunkZ, 20, 0, 48);
			runGenerator(genPyromorphite, world, random, chunkX, chunkZ, 15, 0, 32);
			runGenerator(genIlmenite, world, random, chunkX, chunkZ, 15, 0, 16);
			break;
		}
	}

	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ,
			int chancesToSpawn, int minHeight, int maxHeight) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16);
			generator.generate(world, rand, new BlockPos(x, y, z));
		}
	}

}
