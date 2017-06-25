package furymachinery.client.gui;

import furymachinery.common.tile.TileEntityEnergizedBlock;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiGenerator extends GuiEnergizedBlock{

	public GuiGenerator(Container inventorySlotsIn, TileEntityEnergizedBlock tile, ResourceLocation location) {
		super(inventorySlotsIn, tile, location);
		elements.add(GuiElement.ENERGYBAR0);
		elements.add(GuiElement.ENERGYSLOT0);
	}
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
	}
 
}
