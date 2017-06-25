package furymachinery.client;

import furymachinery.common.CommonProxy;
import furymachinery.common.FuryBlocks;
import furymachinery.common.FuryItems;
import furymachinery.common.FuryMachinery;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenders() {
		FuryItems.registerRenders();
		FuryBlocks.registerRenders();
		NetworkRegistry.INSTANCE.registerGuiHandler(FuryMachinery.instance, new FuryGuiHandler());
	}
}
