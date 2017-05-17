package net.xalcon.energyconverters.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.xalcon.energyconverters.common.tiles.TileEntityTechRebornConsumer;
import reborncore.api.power.EnumPowerTier;

public class BlockConsumerTechReborn extends BlockConverterTechRebornBase implements ITileEntityProvider
{
	public BlockConsumerTechReborn()
	{
		super(Material.IRON, "energy_consumer_tr");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityTechRebornConsumer(EnumPowerTier.values()[meta]);
	}
}
