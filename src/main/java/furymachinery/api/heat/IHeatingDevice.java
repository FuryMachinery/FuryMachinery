package furymachinery.api.heat;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IHeatingDevice {
	public double getHeat();

	public void heat();

	public void cool();
	
	public void moveHeat(EnumFacing side);
	
	public void onOverHeating(World worldIn, BlockPos pos);
}
