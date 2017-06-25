package furymachinery.common.tile;

import java.util.List;

import furymachinery.api.EnumTier;
import furymachinery.api.EnumTransmission;
import furymachinery.api.EnumTransmission.EnumCable;
import furymachinery.api.EnumTransmission.EnumType;
import furymachinery.api.MachineType;
import furymachinery.api.SynchronizedField;
import furymachinery.api.energy.IEnergyHandler;
import furymachinery.api.energy.IEnergyStack;
import furymachinery.api.heat.IHeatingDevice;
import furymachinery.api.util.PosUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import furymachinery.common.block.BlockMachine;;

public abstract class TileEntityEnergizedBlock extends TileEntityBasic
		implements IEnergyHandler, IInventory, SynchronizedField {
	public double energy, capacity, maxTransfer;
	public NonNullList<ItemStack> itemStack;
	public MachineType type;
	public EnumTier tier;
	public EnumFacing inE, outE, outG, inF, inI, outI;

	public TileEntityEnergizedBlock(double capacity, double maxTransfer, MachineType type, EnumTier tier) {
		this.capacity = capacity;
		this.maxTransfer = maxTransfer;
		this.type = type;
		this.tier = tier;
		itemStack = NonNullList.<ItemStack>withSize(tier.getSlotCount(), ItemStack.EMPTY);
		energy = 0;
	}

	@Override
	public boolean canSend(EnumTransmission type, EnumFacing side) {
		if (canTransfer(type, side, EnumType.SEND))
			return true;
		return false;
	}

	@Override
	public boolean canReceive(EnumTransmission type, EnumFacing side) {
		if (canTransfer(type, side, EnumType.RECEIVE))
			return true;
		return false;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return tier.getSlotCount();
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index < 0 || index > getSizeInventory())
			return ItemStack.EMPTY;
		return itemStack.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (!this.getStackInSlot(index).isEmpty()) {
			ItemStack itemstack;

			if (this.getStackInSlot(index).getCount() <= count) {
				itemstack = this.getStackInSlot(index);
				this.setInventorySlotContents(index, ItemStack.EMPTY);
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.getStackInSlot(index).splitStack(count);

				if (this.getStackInSlot(index).getCount() <= 0) {
					this.setInventorySlotContents(index, ItemStack.EMPTY);
				} else {
					this.setInventorySlotContents(index, this.getStackInSlot(index));
				}
				this.markDirty();
				return itemstack;
			}
		} else {
			return ItemStack.EMPTY;
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack itemStack = getStackInSlot(index);
		if (!itemStack.isEmpty())
			setInventorySlotContents(index, ItemStack.EMPTY);
		return itemStack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < 0 || index >= this.getSizeInventory())
			return;
		if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit())
			stack.setCount(this.getInventoryStackLimit());

		if (!stack.isEmpty() && stack.getCount() == 0)
			stack = ItemStack.EMPTY;

		this.itemStack.add(index, stack);
		this.markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.getPos()) == this
				&& player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
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
		for (int i = 0; i < getSizeInventory(); i++)
			this.setInventorySlotContents(i, ItemStack.EMPTY);
	}

	@Override
	public double getEnergy() {
		return energy;
	}

	@Override
	public double getCapacity() {
		return capacity;
	}

	@Override
	public double getMaxTransfer() {
		return maxTransfer;
	}

	@Override
	public void setEnergy(double newEnergy) {
		if (newEnergy > capacity)
			energy = capacity;
		if (newEnergy < 0)
			energy = 0;
		energy = newEnergy;
	}

	@Override
	public double getEnergy(EnumFacing side) {
		if (isHandlerOver(side))
			return getHandlerOver(side).getEnergy();
		return -1;
	}

	@Override
	public double getCapacity(EnumFacing side) {
		if (isHandlerOver(side))
			return getHandlerOver(side).getCapacity();
		return -1;
	}

	@Override
	public double getMaxTranfser(EnumFacing side) {
		if (isHandlerOver(side))
			return getHandlerOver(side).getMaxTransfer();
		return -1;
	}

	@Override
	public void setEnergy(EnumFacing side, double newEnergy) {
		if (isHandlerOver(side))
			getHandlerOver(side).setEnergy(newEnergy);
	}

	@Override
	public MachineType getHandlerType() {
		return type;
	}

	@Override
	public MachineType getHandlerType(EnumFacing side) {
		if (isHandlerOver(side))
			return getHandlerOver(side).getHandlerType();
		return null;
	}

	@Override
	public boolean isHandlerOver(EnumFacing side) {
		BlockPos pos = PosUtil.getPosFrom(side, getPos());
		if (world.getTileEntity(pos) instanceof IEnergyHandler)
			return true;
		return false;
	}

	public IEnergyHandler getHandlerOver(EnumFacing side) {
		if (isHandlerOver(side))
			return (IEnergyHandler) world.getTileEntity(PosUtil.getPosFrom(side, getPos()));
		return null;
	}

	@Override
	public double getDField(int id) {
		switch (id) {
		case 0:
			return energy;
		case 1:
			return capacity;
		case 2:
			return maxTransfer;
		case 3:
			return type.getID();
		}
		return 0;
	}

	@Override
	public void setDField(int id, double value) {
		switch (id) {
		case 0:
			energy = value;
			break;
		case 1:
			capacity = value;
			break;
		case 2:
			maxTransfer = value;
			break;
		case 3:
			type.setID((int) value);
			break;
		}
	}

	@Override
	public int getDFieldCount() {
		return 4;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		energy = compound.getDouble("energy");
		capacity = compound.getDouble("capacity");
		maxTransfer = compound.getDouble("maxTransfer");
		NBTTagList tagList = compound.getTagList("Items", NBT.TAG_COMPOUND);

		for (int tagCount = 0; tagCount < tagList.tagCount(); tagCount++) {
			NBTTagCompound tagCompound = tagList.getCompoundTagAt(tagCount);
			byte slotID = tagCompound.getByte("Slot");

			if (slotID >= 0 && slotID < getSizeInventory()) {
				setInventorySlotContents(slotID, new ItemStack(tagCompound));
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setDouble("energy", energy);
		compound.setDouble("capacity", capacity);
		compound.setDouble("maxTransfer", maxTransfer);
		NBTTagList tagList = new NBTTagList();
		for (int slotCount = 0; slotCount < getSizeInventory(); slotCount++) {
			if (!getStackInSlot(slotCount).isEmpty()) {
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setByte("Slot", (byte) slotCount);
				getStackInSlot(slotCount).writeToNBT(tagCompound);
				tagList.appendTag(tagCompound);
			}
		}
		compound.setTag("items", tagList);
		return compound;
	}

	@Override
	public void update() {
		System.out.println("wou");
	}

	public void chargeItemStack(int index) {
		if (index < 0 || index >= getSizeInventory())
			return;
		if (!getStackInSlot(index).isEmpty() && !(getStackInSlot(index).getItem() instanceof IEnergyStack))
			return;
		else {

		}
	}

	public void addEnergy(double amount) {
		if (energy + amount > capacity)
			this.setEnergy(capacity);
		else if (energy + amount < 0)
			this.setEnergy(0);
		else
			this.setEnergy(energy + amount);
	}

	public void removeEnergy(double amount) {
		addEnergy(-amount);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this;
		return super.getCapability(capability, facing);
	}

}
