package furymachinery.common.tile;

import furymachinery.api.EnumTier;
import furymachinery.api.EnumTransmission;
import furymachinery.api.EnumTransmission.EnumCable;
import furymachinery.api.EnumTransmission.EnumType;
import furymachinery.api.MachineType;
import furymachinery.api.energy.generators.GeneratorType;
import net.minecraft.util.EnumFacing;

public abstract class TileEntityGenerator extends TileEntityEnergizedBlock {
	public GeneratorType type;
	public double generateAmount;

	public TileEntityGenerator(EnumTier tier, GeneratorType type) {
		super(tier.getBaseModifier(), tier.getBaseModifier() / 200, MachineType.GENERATOR, tier);
		this.type = type;
	}

	@Override
	public void update() {
		super.update();
		createEnergy();
	}

	public abstract void createEnergy();

	@Override
	public double getDField(int id) {
		switch (id) {
		case 4:
			return generateAmount;
		default:
			return super.getDField(id);
		}
	}

	@Override
	public void setDField(int id, double value) {
		switch (id) {
		case 4:
			generateAmount = value;
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
