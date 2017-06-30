package furymachinery.common.tile;

import furymachinery.api.EnumTier;
import furymachinery.api.EnumTransmission;
import furymachinery.api.EnumTransmission.EnumCable;
import furymachinery.api.EnumTransmission.EnumType;
import furymachinery.api.energy.IEnergyHandler;
import furymachinery.api.energy.MachineType;
import furymachinery.api.side.SideConfiguration;
import furymachinery.api.util.PosUtil;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

public abstract class TileEntityEnergizedBlock extends TileEntityContainerBlock implements IEnergyHandler {
	protected double energy, capacity, maxTransfer;
	protected MachineType machineType;
	protected SideConfiguration sideConfig;

	public TileEntityEnergizedBlock(EnumTier tier, String name, double capacity, double maxTransfer, double energy,
			MachineType type) {
		super(tier, name);
		this.capacity = capacity;
		this.maxTransfer = maxTransfer;
		this.energy = energy;
		this.machineType = type;
		sideConfig = new SideConfiguration();
	}

	public TileEntityEnergizedBlock(EnumTier tier, String name, double capacity, double maxTransfer, MachineType type) {
		this(tier, name, capacity, maxTransfer, 0, type);
	}

	public void setFacing(EnumFacing side) {
		sideConfig.setFacing(side);
	}

	public void setTransmission(EnumFacing side, EnumTransmission type) {
		sideConfig.setTransmission(side, type);
	}

	public void setInput(EnumFacing side, EnumTransmission type) {
		sideConfig.setInput(side, type);
	}

	public void setOutput(EnumFacing side, EnumTransmission type) {
		sideConfig.setOutput(side, type);
	}

	@Override
	public boolean canConnect(EnumCable type, EnumFacing side) {
		return sideConfig.getTransmissionTypeFor(side).getCable() == type;
	}

	@Override
	public boolean canTransfer(EnumTransmission type, EnumFacing side, EnumType state) {
		return sideConfig.getTransmissionTypeFor(side) == type && sideConfig.getTransmissionStateFor(side) == state;
	}

	@Override
	public boolean canSend(EnumTransmission type, EnumFacing side) {
		return canTransfer(type, side, EnumType.SEND);
	}

	@Override
	public boolean canReceive(EnumTransmission type, EnumFacing side) {
		return canTransfer(type, side, EnumType.RECEIVE);
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
		if (newEnergy > capacity) {
			energy = capacity;
			return;
		}
		if (newEnergy < 0) {
			energy = 0;
			return;
		}
		energy = newEnergy;
	}

	@Override
	public double getEnergy(EnumFacing side) {
		if (world.getTileEntity(PosUtil.getPosFrom(side, getPos())) instanceof TileEntityEnergizedBlock)
			return ((TileEntityEnergizedBlock) world.getTileEntity(PosUtil.getPosFrom(side, getPos()))).getEnergy();
		return 0;
	}

	@Override
	public double getCapacity(EnumFacing side) {
		if (world.getTileEntity(PosUtil.getPosFrom(side, getPos())) instanceof TileEntityEnergizedBlock)
			return ((TileEntityEnergizedBlock) world.getTileEntity(PosUtil.getPosFrom(side, getPos()))).getCapacity();
		return 0;
	}

	@Override
	public double getMaxTranfser(EnumFacing side) {
		if (world.getTileEntity(PosUtil.getPosFrom(side, getPos())) instanceof TileEntityEnergizedBlock)
			return ((TileEntityEnergizedBlock) world.getTileEntity(PosUtil.getPosFrom(side, getPos())))
					.getMaxTransfer();
		return 0;
	}

	@Override
	public void setEnergy(EnumFacing side, double newEnergy) {
		if (world.getTileEntity(PosUtil.getPosFrom(side, getPos())) instanceof TileEntityEnergizedBlock)
			((TileEntityEnergizedBlock) world.getTileEntity(PosUtil.getPosFrom(side, getPos()))).setEnergy(newEnergy);
	}

	@Override
	public MachineType getHandlerType() {
		return machineType;
	}

	@Override
	public MachineType getHandlerType(EnumFacing side) {
		if (world.getTileEntity(PosUtil.getPosFrom(side, getPos())) instanceof TileEntityEnergizedBlock)
			return ((TileEntityEnergizedBlock) world.getTileEntity(PosUtil.getPosFrom(side, getPos())))
					.getHandlerType();
		return null;
	}

	@Override
	public boolean isHandlerOver(EnumFacing side) {
		return world.getTileEntity(PosUtil.getPosFrom(side, getPos())) instanceof IEnergyHandler;
	}

	public void addEnergy(double amount) {
		setEnergy(energy + amount);
	}

	public void removeEnergy(double amount) {
		addEnergy(-amount);
	}

	public void addEnergy(EnumFacing side, double amount) {
		if (world.getTileEntity(PosUtil.getPosFrom(side, getPos())) instanceof TileEntityEnergizedBlock)
			((TileEntityEnergizedBlock) world.getTileEntity(PosUtil.getPosFrom(side, getPos()))).addEnergy(amount);
	}

	public void removeEnergy(EnumFacing side, double amount) {
		addEnergy(side, -amount);
	}
}
