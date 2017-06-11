package net.xalcon.energyconverters.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.xalcon.energyconverters.common.tiles.TileEntityProducerRf;

public class BlockProducerRf extends BlockBase implements ITileEntityProvider
{
	public BlockProducerRf()
	{
		super(Material.IRON, "energy_producer_rf");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityProducerRf();
	}
}
