package net.xalcon.energyconverters.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.xalcon.energyconverters.common.tiles.TileEntityConsumerEu;
import net.xalcon.energyconverters.common.tiles.TileEntityConsumerRf;

public class BlockConsumerRf extends BlockBase implements ITileEntityProvider
{
	public BlockConsumerRf()
	{
		super(Material.IRON, "energy_consumer_rf");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityConsumerRf();
	}
}
