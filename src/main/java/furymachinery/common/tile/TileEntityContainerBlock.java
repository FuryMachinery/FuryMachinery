package furymachinery.common.tile;

import furymachinery.api.EnumTier;
import furymachinery.api.EnumTransmission;
import furymachinery.api.EnumTransmission.EnumCable;
import furymachinery.api.EnumTransmission.EnumType;
import furymachinery.api.SynchronizedField;
import furymachinery.api.energy.IEnergyHandler;
import furymachinery.api.energy.MachineType;
import furymachinery.api.util.LangUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public abstract class TileEntityContainerBlock extends TileEntity implements ITickable, SynchronizedField {
	protected EnumTier tier;
	protected int tierID;
	protected boolean hasCustomName;
	protected String customName, name;
	protected ItemStackHandler stackHandler;

	public TileEntityContainerBlock(EnumTier tier, String name) {
		tierID = tier.getID();
		this.tier = tier;
		this.name = name;
		stackHandler = new ItemStackHandler(tier.getSlotCount()) {
			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
			}
		};
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		if (compound.hasKey("items"))
			stackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
		compound.setInteger("tierID", tierID);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("items", stackHandler.serializeNBT());
		tierID = compound.getInteger("tierID");
		return compound;
	}

	public void setCustomName(String name) {
		hasCustomName = true;
		customName = name;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(LangUtil.match(getName() + "." + tier.getName()));
	}

	public String getName() {
		return name;
	}

	@Override
	public double getDField(int id) {
		switch (id) {
		case 0:
			return tierID;
		}
		return 0;
	}

	@Override
	public void setDField(int id, double value) {
		switch (id) {
		case 0:
			tierID = (int) value;
		}
	}

	@Override
	public int getDFieldCount() {
		return 1;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) stackHandler;
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}

	public boolean canInteractWith(EntityPlayer playerIn) {
		return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5d, 0.5d, 0.5d)) <= 64;
	}

	public ItemStack getStackInSlot(int index) {
		return stackHandler.getStackInSlot(index);
	}
}
