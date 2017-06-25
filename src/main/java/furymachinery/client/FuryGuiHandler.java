package furymachinery.client;

import furymachinery.api.EnumTier;
import furymachinery.client.gui.GuiSolarGenerator;
import furymachinery.common.container.ContainerSolarGenerator;
import furymachinery.common.tile.TileEntitySolarGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class FuryGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
		switch (ID) {
		case 4:
			return new ContainerSolarGenerator(player.inventory, (TileEntitySolarGenerator) tile, EnumTier.BASIC);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
		switch (ID) {
		case 4:
			return new GuiSolarGenerator(player.inventory, (TileEntitySolarGenerator) tile, EnumTier.BASIC);
		}
		return null;
	}

}
