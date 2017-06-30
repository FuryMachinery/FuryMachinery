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
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public abstract class TileEntityContainerBlock extends TileEntity
		implements ITickable, ISidedInventory, SynchronizedField {
	protected NonNullList<ItemStack> tileStacks;
	protected EnumTier tier;
	protected int tierID;
	protected boolean hasCustomName;
	protected String customName, name;
	private IItemHandler handler = new SidedInvWrapper(this, EnumFacing.UP);

	public TileEntityContainerBlock(EnumTier tier, String name) {
		tierID = tier.getID();
		tileStacks = NonNullList.<ItemStack>withSize(tier.getSlotCount(), ItemStack.EMPTY);
		this.tier = tier;
		this.name = name;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		tileStacks = NonNullList.<ItemStack>withSize(tier.getSlotCount(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, tileStacks);
		compound.setInteger("tierID", tierID);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		ItemStackHelper.saveAllItems(compound, tileStacks);
		tierID = compound.getInteger("tierID");
		return compound;
	}

	public NonNullList<ItemStack> getInventory() {
		return tileStacks;
	}

	@Override
	public int getSizeInventory() {
		return getInventory() == null ? 0 : tileStacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack stack : tileStacks) {
			if (!stack.isEmpty())
				return false;
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return getInventory() == null ? ItemStack.EMPTY : tileStacks.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return getInventory() == null ? ItemStack.EMPTY : ItemStackHelper.getAndSplit(tileStacks, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return getInventory() == null ? ItemStack.EMPTY : ItemStackHelper.getAndRemove(tileStacks, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (stack.isEmpty())
			return;
		if (stack.getCount() > getInventoryStackLimit())
			stack.setCount(getInventoryStackLimit());
		getInventory().set(index, stack);
		markDirty();
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
				(double) this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		getInventory().clear();
	}

	@Override
	public boolean hasCustomName() {
		return hasCustomName;
	}

	public void setCustomName(String name) {
		hasCustomName = true;
		customName = name;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(LangUtil.match(getName() + "." + tier.getName()));
	}

	@Override
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
			return (T) handler;
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}

}
