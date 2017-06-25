package furymachinery.common.container;

import furymachinery.api.EnumTier;
import furymachinery.common.FuryItems;
import furymachinery.common.container.slot.FurySlot;
import furymachinery.common.tile.TileEntityGenerator;
import furymachinery.common.tile.TileEntitySolarGenerator;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerSolarGenerator extends ContainerGenerator {

	public ContainerSolarGenerator(InventoryPlayer playerInv, TileEntitySolarGenerator tile, EnumTier tier) {
		super(playerInv, tile, tier);
		switch (tier) {
		case BASIC:
			this.addSlotToContainer(new FurySlot(tile, 1, 44, 30, FuryItems.basicMirror));
			this.addSlotToContainer(new FurySlot(tile, 2, 134, 30, FuryItems.basicMirror));
			break;
		case ADVANCED:
			break;
		case SUPER:
			break;
		case ULTRA:
			break;
		}
	}

}
