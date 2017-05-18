package net.xalcon.energyconverters.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.xalcon.energyconverters.common.tiles.TileEntityProducerFe;
import net.xalcon.energyconverters.common.tiles.TileEntityProducerRf;

public class BlockProducerFe extends BlockBase implements ITileEntityProvider
{
	public BlockProducerFe()
	{
		super(Material.IRON, "energy_producer_fe");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityProducerFe();
	}
}
