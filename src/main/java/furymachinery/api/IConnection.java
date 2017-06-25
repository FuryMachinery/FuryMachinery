package furymachinery.api;

import net.minecraft.util.EnumFacing;

public interface IConnection {
	public boolean canConnect(EnumTransmission.EnumCable type, EnumFacing side);

	public boolean canTransfer(EnumTransmission type, EnumFacing side, EnumTransmission.EnumType state);

	public boolean canSend(EnumTransmission type, EnumFacing side);

	public boolean canReceive(EnumTransmission type, EnumFacing side);
}
