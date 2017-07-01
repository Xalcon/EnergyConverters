package net.xalcon.energyconverters.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.xalcon.energyconverters.common.tiles.TileEntityProducerMj;

import javax.annotation.Nullable;

public class BlockProducerMj extends BlockBase implements ITileEntityProvider
{
	public final static String INTERNAL_NAME = "energy_producer_mj";

	public BlockProducerMj()
	{
		super(Material.IRON, INTERNAL_NAME);
	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing the block.
	 *
	 * @param worldIn
	 * @param meta
	 */
	@Nullable
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityProducerMj();
	}
}