package furymachinery.common.item;

import java.awt.Color;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemSteel extends ItemBasic {
	boolean chromium = false, titanium = false, aluminium = false, vanadium = false;

	public ItemSteel(String name) {
		super(name, 1);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setBoolean("chromium", false);
			stack.getTagCompound().setBoolean("titanium", false);
			stack.getTagCompound().setBoolean("aluminium", false);
			stack.getTagCompound().setBoolean("vanadium", false);

		} else {
			chromium = stack.getTagCompound().getBoolean("chromium");
			stack.getTagCompound().setBoolean("chromium", chromium);
			titanium = stack.getTagCompound().getBoolean("titanium");
			stack.getTagCompound().setBoolean("titanium", titanium);
			aluminium = stack.getTagCompound().getBoolean("aluminium");
			stack.getTagCompound().setBoolean("aluminium", aluminium);
			vanadium = stack.getTagCompound().getBoolean("vanadium");
			stack.getTagCompound().setBoolean("vanadium", vanadium);
		}
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);
	}

	@Override
	public void addActivatedInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip) {
		tooltip.add(stack.getTagCompound().getBoolean("chromium") == false ? "§5No Chromium"
				: "§dChromium (§ainoxidability§d)");
		tooltip.add(stack.getTagCompound().getBoolean("titanium") == false ? "§5No Titanium"
				: "§dTitanium (§athermal resistance§d)");
		tooltip.add(stack.getTagCompound().getBoolean("aluminium") == false ? "§5No Aluminium"
				: "§dAluminium (§apressure resistance§d)");
		tooltip.add(stack.getTagCompound().getBoolean("vanadium") == false ? "§5No Vanadium"
				: "§dVanadium (§achock resistance§d)");
	}

}
