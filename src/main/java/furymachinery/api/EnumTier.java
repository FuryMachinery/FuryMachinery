package furymachinery.api;

import furymachinery.common.item.ItemEnergized;
import net.minecraft.item.ItemStack;

public enum EnumTier {
	BASIC(3, 1d, 1000d), ADVANCED(4, 16d, 20000d), SUPER(6, 128d, 400000d), ULTRA(7, 1024d, 800000000d);
	int count;
	double generateModifier, baseModifier;

	EnumTier(int slotCount, double modifier, double baseModifier) {
		count = slotCount;
		this.generateModifier = modifier;
		this.baseModifier = baseModifier;
	}

	public int getSlotCount() {
		return count;
	}

	public double getGeneratingModifier() {
		return generateModifier;
	}

	public double getBaseModifier() {
		return baseModifier;
	}

	public int getDurability(EnumTier tier) {
		switch (tier) {
		case BASIC:
			return 256;
		case ADVANCED:
			return 1024;
		case SUPER:
			return 8192;
		case ULTRA:
			return 131072;
		}
		return -1;
	}
}
