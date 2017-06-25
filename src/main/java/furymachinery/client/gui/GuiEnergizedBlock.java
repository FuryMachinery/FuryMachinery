package furymachinery.client.gui;

import java.util.ArrayList;
import java.util.List;

import furymachinery.common.FuryMachinery;
import furymachinery.common.tile.TileEntityEnergizedBlock;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiEnergizedBlock extends GuiContainer {
	public ResourceLocation back;
	public List<GuiElement> elements = new ArrayList<GuiElement>();
	public static final ResourceLocation ENERGYBAR0e = new ResourceLocation(FuryMachinery.MODID, "textures/gui/elements/EnergyBar0f.png");

	public GuiEnergizedBlock(Container inventorySlotsIn, TileEntityEnergizedBlock tile, ResourceLocation location) {
		super(inventorySlotsIn);
		back = location;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.mc.getTextureManager().bindTexture(back);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		/*for(GuiElement element : elements){
			mc.getTextureManager().bindTexture(element.getResourceLocation());
			drawTexturedModalRect(element.getX(), element.getY(), 0, 0, element.getXSize(), element.getYSize());
		}*/
	}

}
