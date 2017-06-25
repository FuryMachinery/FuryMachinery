package furymachinery.common.item;

import java.util.List;

import furymachinery.api.EnumTier;
import furymachinery.api.util.InventoryUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBattery extends ItemEnergized {
	public EnumTier tier;

	public ItemBattery(String name, EnumTier tier) {
		super(name, tier.getBaseModifier(), tier.getBaseModifier() / 200, true);
		this.tier = tier;
	}
}
