package furymachinery.client.gui;

import furymachinery.api.EnumTier;
import furymachinery.common.FuryMachinery;
import furymachinery.common.container.ContainerSolarGenerator;
import furymachinery.common.tile.TileEntityEnergizedBlock;
import furymachinery.common.tile.TileEntitySolarGenerator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiSolarGenerator extends GuiGenerator {

	public GuiSolarGenerator(InventoryPlayer playerInv, TileEntitySolarGenerator tile, EnumTier tier) {
		super(new ContainerSolarGenerator(playerInv, tile, tier), tile, getLocationFrom(tier));
		elements.add(GuiElement.SLOTNORMAL0);
		elements.add(GuiElement.SLOTNORMAL1);
	}

	public static ResourceLocation getLocationFrom(EnumTier tier) {
		switch (tier) {
		case BASIC:
			return new ResourceLocation(FuryMachinery.MODID, "textures/gui/BasicGeneratorS.png");
		case ADVANCED:
			return new ResourceLocation(FuryMachinery.MODID, "textures/gui/AdvancedGeneratorS.png");
		case SUPER:
			return new ResourceLocation(FuryMachinery.MODID, "textures/gui/SuperGeneratorS.png");
		case ULTRA:
			return new ResourceLocation(FuryMachinery.MODID, "textures/gui/UltraGeneratorS.png");
		}
		return new ResourceLocation(FuryMachinery.MODID, "textures/gui/BasicGeneratorS.png");
	}

}
