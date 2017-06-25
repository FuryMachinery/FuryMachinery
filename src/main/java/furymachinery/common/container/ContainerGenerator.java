package furymachinery.common.container;

import furymachinery.api.EnumTier;
import furymachinery.common.FuryItems;
import furymachinery.common.container.slot.FurySlot;
import furymachinery.common.tile.TileEntityEnergizedBlock;
import furymachinery.common.tile.TileEntityGenerator;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerGenerator extends ContainerEnergizedBlock {

	protected ContainerGenerator(InventoryPlayer playerInv, TileEntityGenerator tile, EnumTier tier) {
		super(playerInv, tile, tier);
		this.addSlotToContainer(new FurySlot(tile, 0, 8, 61, FuryItems.basicBattery));
	}

}
