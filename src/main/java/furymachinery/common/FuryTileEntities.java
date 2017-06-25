package furymachinery.common;

import furymachinery.common.tile.TileEntityEnergizedBlock;
import furymachinery.common.tile.TileEntitySolarGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FuryTileEntities {
	public static void register(){
		GameRegistry.registerTileEntity(TileEntitySolarGenerator.class, "gs");
	}
}
