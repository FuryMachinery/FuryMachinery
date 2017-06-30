package furymachinery.common.tile;

import furymachinery.api.EnumTier;
import furymachinery.api.energy.MachineType;
import furymachinery.api.energy.generators.GeneratorType;
import furymachinery.common.item.ItemEnergized;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class TileEntityGenerator extends TileEntityEnergizedBlock {
	GeneratorType generatorType;
	protected double generateAmount = 0;

	public TileEntityGenerator(EnumTier tier, String name, double capacity, double maxTransfer, double energy,
			GeneratorType type) {
		super(tier, name, capacity, maxTransfer, energy, MachineType.GENERATOR);
		generatorType = type;
	}

	@Override
	public void update() {
		super.update();
		updateGenerateAmount();
		createEnergy();
		sendEnergy(0);
		if (tier == EnumTier.SUPER || tier == EnumTier.ULTRA)
			sendEnergy(4);
	}

	public abstract void createEnergy();

	public abstract void updateGenerateAmount();

	public void sendEnergy(int index) {
		if (!getStackInSlot(index).isEmpty()) {
			if (getStackInSlot(index).getItem() instanceof ItemEnergized) {
				ItemStack stack = new ItemStack((ItemEnergized) getStackInSlot(index).getItem());
				ItemEnergized item = (ItemEnergized) getStackInSlot(index).getItem();
				if (stack.getTagCompound() == null)
					stack.setTagCompound(new NBTTagCompound());
				double stackEnergy = item.getEnergy(stack);
				double stackCapacity = item.getCapacity(stack);
				double stackMaxTransfer = item.getMaxTransfer(stack);
				double maxTransfer = Math.min(stackMaxTransfer, getMaxTransfer());
				if (stackEnergy >= stackCapacity || getEnergy() <= 0)
					return;
				else {
					double energyToTransfer = stackCapacity - stackEnergy < maxTransfer
							? (getEnergy() < stackCapacity - stackEnergy ? getEnergy() : stackCapacity - stackEnergy)
							: (getEnergy() < maxTransfer ? getEnergy() : maxTransfer);
					item.addEnergy(stack, energyToTransfer);
					item.setChange(stack, energyToTransfer);
					if (item.isDamageable(stack))
						item.setDamage(stack,
								1000 - (item.getEnergy(stack) <= 0 ? 0
										: (item.getEnergy(stack) >= capacity ? 999
												: (int) (item.getEnergy(stack) / (item.getCapacity(stack) / 1000)))));
					stackHandler.setStackInSlot(index, stack);
					removeEnergy(energyToTransfer);
				}
			} else
				return;
		} else
			return;
	}

	@Override
	public double getDField(int id) {
		switch (id) {
		case 4:
			return generateAmount;
		}
		return super.getDField(id);
	}

	@Override
	public void setDField(int id, double value) {
		switch (id) {
		case 4:
			generateAmount = value;
			break;
		}
		super.setDField(id, value);
	}
	@Override
	public int getDFieldCount() {
		return super.getDFieldCount() + 1;
	}

}
