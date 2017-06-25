package furymachinery.common;

import furymachinery.api.EnumTier;
import furymachinery.api.MachineID;
import furymachinery.common.block.BlockMachine;
import furymachinery.common.block.BlockOre;
import furymachinery.common.tile.TileEntitySolarGenerator;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FuryBlocks {
	public static Block aurichalciteOre, lislkirchneriteOre, pyromorphiteOre, ilmeniteOre, test0;

	public static void init() {
		aurichalciteOre = new BlockOre("oreaurichalcite", 0);
		lislkirchneriteOre = new BlockOre("orelislkirchnerite", 1);
		pyromorphiteOre = new BlockOre("orepyromorphite", 2);
		ilmeniteOre = new BlockOre("oreilmenite", 3);
		test0 = new BlockMachine("test10", new TileEntitySolarGenerator(EnumTier.BASIC), MachineID.GENERATORSB);
	}

	public static void register() {
		registerBlock(aurichalciteOre);
		registerBlock(lislkirchneriteOre);
		registerBlock(pyromorphiteOre);
		registerBlock(ilmeniteOre);
		registerBlock(test0);
	}

	public static void registerRenders() {
		registerRender(aurichalciteOre);
		registerRender(lislkirchneriteOre);
		registerRender(pyromorphiteOre);
		registerRender(ilmeniteOre);
		registerRender(test0);
	}

	public static void registerRender(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

	private static void registerBlock(Block block) {
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block), block.getRegistryName());
	}
}
