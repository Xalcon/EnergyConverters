package net.xalcon.energyconverters.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.xalcon.energyconverters.common.tiles.TileEntityTeslaProducer;

import javax.annotation.Nonnull;

public class BlockProducerTesla extends BlockBase implements ITileEntityProvider
{
	public final static String INTERNAL_NAME = "energy_producer_tesla";

	public BlockProducerTesla()
	{
		super(Material.IRON, INTERNAL_NAME);
	}

	@Override
	@Nonnull
	public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta)
	{
		return new TileEntityTeslaProducer();
	}
}
