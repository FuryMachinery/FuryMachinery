package furymachinery.client.gui;

import furymachinery.common.FuryMachinery;
import net.minecraft.util.ResourceLocation;

public enum GuiElement {
	SLOTNORMAL0(43, 29, "SlotNormal.png", 18, 18), SLOTNORMAL1(133, 29, "SlotNormal.png", 18, 18), ENERGYBAR0(8, 5,
			"EnergyBar0e.png", 16, 52), ENERGYSLOT0(7, 60, "SlotEnergy.png", 18, 18);
	ResourceLocation location;
	int x, y, xSize, ySize;

	GuiElement(int x, int y, String textureName, int xSize, int ySize) {
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		location = new ResourceLocation(FuryMachinery.MODID, "textures/gui/elements/" + textureName);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getXSize() {
		return xSize;
	}

	public int getYSize() {
		return ySize;
	}

	public ResourceLocation getResourceLocation() {
		return location;
	}
}
