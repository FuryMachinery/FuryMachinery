package furymachinery.common.container.slot;

import furymachinery.common.item.ItemBasic;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FurySlot extends Slot {
	public Item validItem;

	public FurySlot(IInventory inventoryIn, int index, int xPosition, int yPosition, Item itemValid) {
		super(inventoryIn, index, xPosition, yPosition);
		validItem = itemValid;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() == validItem;
	}
}
