package net.xalcon.energyconverters.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.xalcon.energyconverters.common.tiles.TileEntityConsumerEu;

public class BlockConsumerEu extends BlockConverterEuBase implements ITileEntityProvider
{
	public BlockConsumerEu()
	{
		super(Material.IRON, "energy_consumer_eu");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityConsumerEu(meta + 1);
	}
}
