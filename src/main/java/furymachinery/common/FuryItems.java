package furymachinery.common;

import furymachinery.api.EnumTier;
import furymachinery.common.item.ItemBasic;
import furymachinery.common.item.ItemBattery;
import furymachinery.common.item.ItemEnergized;
import furymachinery.common.item.ItemMirror;
import furymachinery.common.item.ItemSteel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FuryItems {
	public static Item zincIngot, copperIngot, aluminiumIngot, leadIngot, vanadiumIngot, chromiumIngot, silverIngot,
			titaniumIngot, steelIngot, basicMirror, basicBattery, test0, bm;

	public static void init() {
		zincIngot = new ItemBasic("ingotzinc");
		copperIngot = new ItemBasic("ingotcopper");
		aluminiumIngot = new ItemBasic("ingotaluminium");
		leadIngot = new ItemBasic("ingotlead");
		vanadiumIngot = new ItemBasic("ingotvanadium");
		chromiumIngot = new ItemBasic("ingotchromium");
		silverIngot = new ItemBasic("ingotsilver");
		titaniumIngot = new ItemBasic("ingottitanium");
		steelIngot = new ItemSteel("ingotsteel");
		basicMirror = new ItemMirror("mirrorbasic", EnumTier.BASIC);
		basicBattery = new ItemBattery("batterybasic", EnumTier.BASIC);
		test0 = new ItemEnergized("test0", 14654175469d, 15566, true);
		bm = new ItemMirror("bm", EnumTier.ADVANCED);
	}

	public static void register() {
		registerItem(zincIngot);
		registerItem(copperIngot);
		registerItem(aluminiumIngot);
		registerItem(leadIngot);
		registerItem(vanadiumIngot);
		registerItem(chromiumIngot);
		registerItem(silverIngot);
		registerItem(titaniumIngot);
		registerItem(steelIngot);
		registerItem(basicMirror);
		registerItem(test0);
		registerItem(bm);
	}

	public static void registerRenders() {
		registerRender(zincIngot);
		registerRender(copperIngot);
		registerRender(aluminiumIngot);
		registerRender(leadIngot);
		registerRender(vanadiumIngot);
		registerRender(chromiumIngot);
		registerRender(silverIngot);
		registerRender(titaniumIngot);
		registerRender(steelIngot);
		registerRender(basicMirror);
		registerRender(test0);
		registerRender(bm);
	}

	public static void registerItem(Item item) {
		GameRegistry.register(item);
	}

	public static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
