package furymachinery.api.energy;

import net.minecraft.item.ItemStack;

public interface IEnergyStack {
	public double getEnergy(ItemStack stack);

	public double getCapacity(ItemStack stack);

	public double getMaxTransfer(ItemStack stack);

	public void setEnergy(ItemStack stack, double amount);
}
