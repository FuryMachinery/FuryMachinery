package furymachinery.common.item;

import java.util.List;

import furymachinery.api.EnumTier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemMirror extends ItemBasic {
	public EnumTier tier;
	boolean isBeingUsed = false;
	int uses;

	public ItemMirror(String name, EnumTier tier) {
		super(name, true);
		this.tier = tier;
		this.setMaxDamage(tier.getDurability(tier));
		uses = tier.getDurability(tier);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		if (worldIn.isRemote)
			return;
		if (stack.getTagCompound() == null)
			stack.setTagCompound(new NBTTagCompound());
		if (stack.getItemDamage() <= 0)
			this.setDamage(stack, 1);
		if (stack.getTagCompound().getInteger("uses") == 0)
			stack.getTagCompound().setInteger("uses", uses);
		isBeingUsed = stack.getTagCompound().getBoolean("used");
		uses = stack.getTagCompound().getInteger("uses");
		stack.getTagCompound().setBoolean("used", isBeingUsed);
		stack.getTagCompound().setInteger("uses", uses);
	}

	public EnumTier getTier() {
		return tier;
	}

	@Override
	public void addActivatedInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip) {
		tooltip.add("§dMirrors are used in solar generators to increase the energy production.");
		tooltip.add("§a" + stack.getTagCompound().getInteger("uses") + "§1 / §a" + tier.getDurability(tier));
	}

}
