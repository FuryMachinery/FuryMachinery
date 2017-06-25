package furymachinery.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FurySlotAdvanced extends FurySlot {

	public FurySlotAdvanced(IInventory inventoryIn, int index, int xPosition, int yPosition, Item itemValid) {
		super(inventoryIn, index, xPosition, yPosition, itemValid);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem().getClass() == validItem.getClass();
	}

}
