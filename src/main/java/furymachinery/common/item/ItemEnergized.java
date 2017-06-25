package furymachinery.common.item;

import java.util.List;

import furymachinery.api.energy.IEnergyStack;
import furymachinery.api.util.NumberUtil;
import jline.internal.Log;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemEnergized extends ItemBasic implements IEnergyStack {
	double energy, capacity, maxTransfer, change;
	boolean isDamageable;

	public ItemEnergized(String name, double capacity, double maxTransfer, boolean isDamageable) {
		super(name, 2);
		energy = 0;
		this.capacity = capacity;
		this.maxTransfer = maxTransfer;
		this.isDamageable = isDamageable;
		this.setMaxStackSize(1);
		if (isDamageable)
			this.setMaxDamage(1000);

	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!(entityIn instanceof EntityPlayer) || !worldIn.isRemote)
			return;
		if (stack.getTagCompound() == null)
			stack.setTagCompound(new NBTTagCompound());
		energy = stack.getTagCompound().getDouble("energy");
		change = stack.getTagCompound().getDouble("change");
		stack.getTagCompound().setDouble("capacity", capacity);
		stack.getTagCompound().setDouble("maxTransfer", maxTransfer);
		stack.getTagCompound().setDouble("energy", energy);
		stack.getTagCompound().setDouble("change", change);
		if (isDamageable)
			this.setDamage(stack, 1000 - (getEnergy(stack) <= 0 ? 0
					: (getEnergy(stack) >= capacity ? 999 : (int) (getEnergy(stack) / (getCapacity(stack) / 1000)))));
		addEnergy(stack, 100000d);
		stack.getTagCompound().setDouble("change", 100000d);
	}

	@Override
	public double getEnergy(ItemStack stack) {
		if (stack.getTagCompound() == null)
			return 0;
		return stack.getTagCompound().getDouble("energy");
	}

	@Override
	public double getCapacity(ItemStack stack) {
		if (stack.getTagCompound() == null)
			return 0;
		return stack.getTagCompound().getDouble("capacity");
	}

	@Override
	public double getMaxTransfer(ItemStack stack) {
		if (stack.getTagCompound() == null)
			return 0;
		return stack.getTagCompound().getDouble("maxTransfer");
	}

	@Override
	public void setEnergy(ItemStack stack, double amount) {
		if (stack.getTagCompound() == null)
			return;
		if (amount > getCapacity(stack))
			stack.getTagCompound().setDouble("energy", getCapacity(stack));
		else if (amount < 0)
			stack.getTagCompound().setDouble("energy", 0);
		else
			stack.getTagCompound().setDouble("energy", amount);

	}

	public void addEnergy(ItemStack stack, double amount) {
		if (stack.getTagCompound() == null)
			return;
		stack.getTagCompound().setDouble("energy", getEnergy(stack) + amount);
	}

	public double getChange(ItemStack stack) {
		if (stack.getTagCompound() == null)
			return 0;
		return stack.getTagCompound().getDouble("change");
	}

	@Override
	public void addActivated2Information(ItemStack stack, EntityPlayer playerIn, List<String> tooltip) {
		tooltip.add("§6Energy: " + NumberUtil.getFormattedEnergy(getEnergy(stack)) + " §c/ "
				+ NumberUtil.getFormattedEnergy(getCapacity(stack)));
		tooltip.add("§6" + (getChange(stack) == 0 ? "No energy change."
				: (getChange(stack) < 0 ? "§6Sending: " + NumberUtil.getFormattedEnergy(-getChange(stack))
						: "§6Receiving: " + NumberUtil.getFormattedEnergy(getChange(stack)))));
	}

}
