package furymachinery.api.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PosUtil {

	public static BlockPos getPosFrom(EnumFacing side, BlockPos pos) {
		int local = side.getAxisDirection() == EnumFacing.AxisDirection.NEGATIVE ? -1 : 1;
		int x = pos.getX() + (side.getAxis() == EnumFacing.Axis.X ? local : 0),
				y = pos.getY() + (side.getAxis() == EnumFacing.Axis.Y ? local : 0),
				z = pos.getZ() + (side.getAxis() == EnumFacing.Axis.Z ? local : 0);
		return new BlockPos(x, y, z);
	}

	public static boolean canSeeSky(World worldIn, BlockPos pos) {
		if (worldIn.provider.getDimension() == 0) {
			boolean local1 = true;
			for (int i = pos.getY() + 1; i <= 256; i++) {
				Block block = worldIn.getBlockState(new BlockPos(pos.getX(), i, pos.getZ())).getBlock();
				if (block == Blocks.AIR || block == Blocks.GLASS || block == Blocks.STAINED_GLASS
						|| block == Blocks.GLASS_PANE || block == Blocks.STAINED_GLASS_PANE) {
					local1 = true;
					continue;
				} else {
					local1 = false;
					break;
				}

			}
			return local1;
		}
		return false;
	}
}
