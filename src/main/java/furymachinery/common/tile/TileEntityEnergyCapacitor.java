package furymachinery.common.tile;

import furymachinery.api.EnumTier;
import furymachinery.api.EnumTransmission;
import furymachinery.api.EnumTransmission.EnumCable;
import furymachinery.api.EnumTransmission.EnumType;
import furymachinery.api.MachineType;
import net.minecraft.util.EnumFacing;

public abstract class TileEntityEnergyCapacitor extends TileEntityEnergizedBlock {

	protected TileEntityEnergyCapacitor(double capacity, double maxTransfer, MachineType type, EnumTier tier) {
		super(capacity, maxTransfer, MachineType.CAPACITOR, tier);
	}

	@Override
	public void update() {
		chargeItemStack(1);
		chargeItemStack(2);
		if (tier != EnumTier.BASIC) {
			chargeItemStack(3);
			chargeItemStack(4);
			if (tier != EnumTier.ADVANCED) {
				chargeItemStack(5);
				if (tier != EnumTier.ULTRA) {
					chargeItemStack(6);
					chargeItemStack(7);
					chargeItemStack(8);
				}
			}
		}
	}
}
