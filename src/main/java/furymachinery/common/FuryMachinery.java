package furymachinery.common;

import furymachinery.client.network.message.MessageUpdateContainerDouble;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = "furymachinery", name = FuryMachinery.NAME, useMetadata = true, version = FuryMachinery.VERSION)
public class FuryMachinery {
	public static final String MODID = "furymachinery", NAME = "Fury Machinery", VERSION = "1.0 B",
			CLIENT_PROXY = "furymachinery.client.ClientProxy", COMMON_PROXY = "furymachinery.common.CommonProxy";
	@SidedProxy(clientSide = FuryMachinery.CLIENT_PROXY, serverSide = FuryMachinery.COMMON_PROXY)
	public static CommonProxy proxy;
	@Instance(FuryMachinery.MODID)
	public static FuryMachinery instance;
	public static SimpleNetworkWrapper NETWORK;
	public static final CreativeTabs tabFuryMachinery1 = new CreativeTabs(FuryMachinery.MODID + "1") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Blocks.BRICK_BLOCK);
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		FuryItems.init();
		FuryItems.register();
		FuryBlocks.init();
		FuryBlocks.register();
		FuryTileEntities.register();
		NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(FuryMachinery.MODID);
		NETWORK.registerMessage(MessageUpdateContainerDouble.Handler.class, MessageUpdateContainerDouble.class, 0,
				Side.CLIENT);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
		proxy.registerRenders();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

	}
}
