package net.xalcon.energyconverters.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.xalcon.energyconverters.common.tiles.TileEntityProducerEu;

public class BlockProducerEu extends BlockConverterEuBase implements ITileEntityProvider
{
	public final static String INTERNAL_NAME = "energy_producer_eu";

	public BlockProducerEu()
	{
		super(Material.IRON, INTERNAL_NAME);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityProducerEu(meta + 1);
	}
}
