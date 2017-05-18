package net.xalcon.energyconverters.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.xalcon.energyconverters.common.tiles.TileEntityConsumerFe;
import net.xalcon.energyconverters.common.tiles.TileEntityConsumerRf;

public class BlockConsumerFe extends BlockBase implements ITileEntityProvider
{
	public BlockConsumerFe()
	{
		super(Material.IRON, "energy_consumer_fe");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityConsumerFe();
	}
}
