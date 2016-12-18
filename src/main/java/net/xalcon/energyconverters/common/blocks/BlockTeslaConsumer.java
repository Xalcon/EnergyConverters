package net.xalcon.energyconverters.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.xalcon.energyconverters.common.tiles.TileEntityTeslaConsumer;

import javax.annotation.Nonnull;

public class BlockTeslaConsumer extends BlockBase implements ITileEntityProvider
{
	public BlockTeslaConsumer()
	{
		super(Material.IRON, "energy_consumer_tesla");
	}

	@Override
	@Nonnull
	public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta)
	{
		return new TileEntityTeslaConsumer();
	}
}
