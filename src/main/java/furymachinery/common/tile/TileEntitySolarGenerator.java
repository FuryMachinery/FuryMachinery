package furymachinery.common.tile;

import furymachinery.api.EnumTier;
import furymachinery.api.EnumTransmission;
import furymachinery.api.EnumTransmission.EnumCable;
import furymachinery.api.EnumTransmission.EnumType;
import furymachinery.api.energy.generators.GeneratorType;
import furymachinery.api.MachineType;
import furymachinery.api.util.PosUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import furymachinery.common.block.BlockMachine;
import furymachinery.common.item.ItemEnergized;
import furymachinery.common.item.ItemMirror;
import furymachinery.common.item.ItemUpgrade;

public class TileEntitySolarGenerator extends TileEntityGenerator {

	public TileEntitySolarGenerator(EnumTier tier) {
		super(tier, GeneratorType.SOLAR);
	}

	@Override
	public boolean canConnect(EnumCable type, EnumFacing side) {
		return side == ((BlockMachine) getWorld().getBlockState(getPos()).getBlock())
				.getFacing(getWorld().getBlockState(getPos())) && type == EnumCable.ENERGY;
	}

	@Override
	public boolean canTransfer(EnumTransmission type, EnumFacing side, EnumType state) {
		if (type != EnumTransmission.ENERGY)
			return false;
		if (side != ((BlockMachine) getWorld().getBlockState(getPos()).getBlock())
				.getFacing(getWorld().getBlockState(getPos())))
			return false;
		if (state != EnumType.SEND)
			return false;
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index == 0) {
			if (stack.getItem() instanceof ItemEnergized)
				return stack.getItem() instanceof ItemMirror && ((ItemMirror) stack.getItem()).getTier() == tier;
		} else if (index == 1) {
			if (tier != EnumTier.BASIC) {
				return stack.getItem() instanceof ItemUpgrade;
			} else
				return stack.getItem() instanceof ItemMirror && ((ItemMirror) stack.getItem()).getTier() == tier;
		} else {
			switch (tier) {
			case BASIC:
				return stack.getItem() instanceof ItemMirror && ((ItemMirror) stack.getItem()).getTier() == tier;
			case ADVANCED:
				return stack.getItem() instanceof ItemMirror && ((ItemMirror) stack.getItem()).getTier() == tier;
			case SUPER:
				return stack.getItem() instanceof ItemMirror && ((ItemMirror) stack.getItem()).getTier() == tier;
			case ULTRA:
				return stack.getItem() instanceof ItemMirror && ((ItemMirror) stack.getItem()).getTier() == tier;
			}
		}
		return false;
	}

	public void createEnergy() {
		if (PosUtil.canSeeSky(getWorld(), getPos())) {
			double generateAmount = (getMirrorCount() * tier.getGeneratingModifier()) + tier.getGeneratingModifier()
					+ getMirrorCount();
			this.generateAmount = generateAmount;
			addEnergy(generateAmount);

		}
	}

	public int getMirrorCount() {
		int all = 0;
		if (!getStackInSlot(2).isEmpty())
			all++;
		switch (tier) {
		case BASIC:
			if (!getStackInSlot(1).isEmpty())
				all++;
			break;
		case ADVANCED:
			if (!getStackInSlot(3).isEmpty())
				all++;
			break;
		case SUPER:
			if (!getStackInSlot(3).isEmpty())
				all++;
			if (!getStackInSlot(4).isEmpty())
				all++;
			if (!getStackInSlot(5).isEmpty())
				all++;
			break;
		case ULTRA:
			if (!getStackInSlot(3).isEmpty())
				all++;
			if (!getStackInSlot(4).isEmpty())
				all++;
			if (!getStackInSlot(5).isEmpty())
				all++;
			if (!getStackInSlot(6).isEmpty())
				all++;
			break;
		}
		return all;
	}

	@Override
	public double getDField(int id) {
		switch (id) {
		case 5:
			return getMirrorCount();
		default:
			return super.getDField(id);
		}
	}

	@Override
	public void setDField(int id, double value) {
		switch (id) {
		case 5: 
			break;
		default:
			super.setDField(id, value);
			break;
		}

	}

	@Override
	public int getDFieldCount() {
		return super.getDFieldCount() + 1;
	}

}
