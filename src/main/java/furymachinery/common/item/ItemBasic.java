package furymachinery.common.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import furymachinery.common.FuryMachinery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemBasic extends Item {
	boolean hasInformation;
	int infCount = 0;

	public ItemBasic(String name) {
		this.setUnlocalizedName(name);
		this.setRegistryName(FuryMachinery.MODID, name);
		this.setCreativeTab(FuryMachinery.tabFuryMachinery1);
	}

	protected ItemBasic(String name, boolean hasInformation) {
		this(name);
		this.hasInformation = hasInformation;
	}

	protected ItemBasic(String name, int informationsCount) {
		this(name, true);
		infCount = informationsCount;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		boolean hasToDisplayH = true;
		if (stack.getTagCompound() == null)
			return;
		if (!hasInformation)
			return;
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && !Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			hasToDisplayH = false;
			addActivatedInformation(stack, playerIn, tooltip);
		}
		if (infCount >= 2 && Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			hasToDisplayH = false;
			addActivated2Information(stack, playerIn, tooltip);
		}
		if (hasToDisplayH) {
			tooltip.add("§6Press §1LSHIFT §6to display item informations.");
			if (infCount >= 2)
				tooltip.add("§6Press §1LCONTROL §6to display energy informations");
		}

	}

	public void addActivatedInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip) {

	}

	public void addActivated2Information(ItemStack stack, EntityPlayer playerIn, List<String> tooltip) {

	}
}
