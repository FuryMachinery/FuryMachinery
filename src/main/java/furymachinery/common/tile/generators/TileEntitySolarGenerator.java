package furymachinery.common.tile.generators;

import furymachinery.api.EnumTier;
import furymachinery.api.energy.generators.GeneratorType;
import furymachinery.api.util.PosUtil;
import furymachinery.common.tile.TileEntityGenerator;

public class TileEntitySolarGenerator extends TileEntityGenerator {
	private int mirrorCount;
	private int canSeeSky;

	public TileEntitySolarGenerator(EnumTier tier, double energy) {
		super(tier, "SolarGenerator." + tier.getName(), tier.getBaseModifier(), tier.getBaseModifier() / 200, energy,
				GeneratorType.SOLAR);
		mirrorCount = 0;
	}

	@Override
	public void createEnergy() {
		if (PosUtil.canSeeSky(getWorld(), getPos())) {
			canSeeSky = 1;
			addEnergy(generateAmount);
		} else
			canSeeSky = 0;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void updateGenerateAmount() {
		boolean local1 = false, local2 = false, local3 = false;
		switch (tier) {
		case ADVANCED:
			local1 = true;
			break;
		case SUPER:
			local2 = true;
			break;
		case ULTRA:
			local3 = true;
			break;
		}
		if (!getStackInSlot(1).isEmpty())
			mirrorCount++;
		if (!getStackInSlot(2).isEmpty())
			mirrorCount++;
		if (local1 && !getStackInSlot(3).isEmpty())
			mirrorCount++;
		if (local2 && !getStackInSlot(5).isEmpty())
			mirrorCount++;
		if (local3 && !getStackInSlot(6).isEmpty())
			mirrorCount++;
		double tierModifier = tier.getBaseModifier() / 1000;
		generateAmount = tierModifier + mirrorCount + (tierModifier * mirrorCount);
	}

	@Override
	public double getDField(int id) {
		switch (id) {
		case 5:
			return mirrorCount;
		case 6:
			return canSeeSky;
		}
		return super.getDField(id);
	}

	@Override
	public void setDField(int id, double value) {
		switch (id) {
		case 5:
			mirrorCount = (int) value;
			break;
		case 6:
			canSeeSky = (int) value;
		}
		super.setDField(id, value);
	}

	@Override
	public int getDFieldCount() {
		return super.getDFieldCount() + 2;
	}

	public class TileEntitySolarGeneratorBasic extends TileEntitySolarGenerator {

		public TileEntitySolarGeneratorBasic(double energy) {
			super(EnumTier.BASIC, energy);
		}

	}

	public class TileEntitySolarGeneratorAdvanced extends TileEntitySolarGenerator {

		public TileEntitySolarGeneratorAdvanced(double energy) {
			super(EnumTier.ADVANCED, energy);
		}

	}

	public class TileEntitySolarGeneratorSuper extends TileEntitySolarGenerator {

		public TileEntitySolarGeneratorSuper(double energy) {
			super(EnumTier.SUPER, energy);
		}

	}

	public class TileEntitySolarGeneratorUltra extends TileEntitySolarGenerator {

		public TileEntitySolarGeneratorUltra(double energy) {
			super(EnumTier.ULTRA, energy);
		}

	}
}
