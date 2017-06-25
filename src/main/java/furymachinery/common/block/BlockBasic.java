package furymachinery.common.block;

import furymachinery.api.util.PosUtil;
import furymachinery.common.FuryMachinery;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class BlockBasic extends Block {

	public BlockBasic(Material materialIn, String name, float hardness, int harvestLevel, int harvestType) {
		super(materialIn);
		this.setCreativeTab(FuryMachinery.tabFuryMachinery1);
		this.setHardness(hardness);
		this.setHarvestLevel(harvestType, harvestLevel);
		this.setUnlocalizedName(name);
		this.setRegistryName(FuryMachinery.MODID + ":" + name);

	}

	public void setHarvestLevel(int harvestType, int harvestLevel) {
		switch (harvestType) {
		case 0:
			this.setHarvestLevel("pickaxe", harvestLevel);
			break;
		case 1:
			this.setHarvestLevel("axe", harvestLevel);
			break;
		case 2:
			this.setHarvestLevel("shovel", harvestLevel);
		default:
			this.setHarvestLevel("pickaxe", harvestLevel);
			;
		}
	}
}
