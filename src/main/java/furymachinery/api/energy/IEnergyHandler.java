package furymachinery.api.energy;

import furymachinery.api.IConnection;
import furymachinery.api.MachineType;
import net.minecraft.util.EnumFacing;

public interface IEnergyHandler extends IConnection {
	public double getEnergy();

	public double getCapacity();

	public double getMaxTransfer();

	public void setEnergy(double newEnergy);

	public double getEnergy(EnumFacing side);

	public double getCapacity(EnumFacing side);

	public double getMaxTranfser(EnumFacing side);

	public void setEnergy(EnumFacing side, double newEnergy);

	public MachineType getHandlerType();

	public MachineType getHandlerType(EnumFacing side);

	public boolean isHandlerOver(EnumFacing side);
}
