package furymachinery.common.block;

import java.util.ArrayList;
import java.util.List;

import furymachinery.common.FuryMachinery;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockOre extends BlockBasic {

	public BlockOre(String name, int meta) {
		super(Material.ROCK, name, 12.554f, 2, 0);
	}
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> drops = new ArrayList<ItemStack>();
		return super.getDrops(world, pos, state, fortune);
	}
	/*
	 * aurichalcite
	 * Zinc, Carbone, Cuivre
	 * CO, Oxygène, Hydrogène
	 * 
	 * lislkirchnerite
	 * Aluminium, Germanium, Plomb
	 * N2O, Azote, Hydrogène
	 * 
	 * pyromorphite
	 * Potassium, Vanadium, Chrome, Radium
	 * ClO2, Chlore, Hélium
	 * 
	 * ilmenite
	 * Magnésium, Manganèse, Argent, Titane
	 * N2O4, Oxygène, Fluor
	 * 
	 * Zinc, Cuivre, Aluminium, Germanium, Silicium, Plomb, Potassium, Vanadium, Chrome, Radium, Argent, Titane
	 */
	
}
