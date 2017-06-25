package furymachinery.api.util;

import furymachinery.api.energy.IEnergyStack;
import furymachinery.common.item.ItemEnergized;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryUtil {
	public static boolean isEnergizedItemIn(IInventory inv) {
		for (int index = 0; index < inv.getSizeInventory(); index++) {
			if (inv.getStackInSlot(index) == ItemStack.EMPTY)
				continue;
			if (inv.getStackInSlot(index).getItem() instanceof IEnergyStack)
				return true;
			else
				continue;
		}
		return false;
	}

	public static int getEnergizedItemSlot(IInventory inv) {
		if (!isEnergizedItemIn(inv))
			return -1;
		for (int index = 0; index < inv.getSizeInventory(); index++) {
			if (inv.getStackInSlot(index) == ItemStack.EMPTY)
				continue;
			if (inv.getStackInSlot(index).getItem() instanceof IEnergyStack)
				return index;
			else
				continue;
		}
		return -1;
	}

	public static Class<? extends Item> getItemClass(IInventory inv) {
		if (!isEnergizedItemIn(inv))
			return null;
		for (int index = 0; index < inv.getSizeInventory(); index++) {
			if (inv.getStackInSlot(index) == ItemStack.EMPTY)
				continue;
			if (inv.getStackInSlot(index).getItem() instanceof IEnergyStack)
				return inv.getStackInSlot(index).getItem().getClass();
			else
				continue;
		}
		return null;
	}
	public static ItemStack getEnergizedItem(IInventory inv){
		if (!isEnergizedItemIn(inv))
			return null;
		for (int index = 0; index < inv.getSizeInventory(); index++) {
			if (inv.getStackInSlot(index) == ItemStack.EMPTY)
				continue;
			if (inv.getStackInSlot(index).getItem() instanceof IEnergyStack)
				return inv.getStackInSlot(index);
			else
				continue;
		}
		return null;
	}
}
