package net.xalcon.energyconverters.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.xalcon.energyconverters.common.tiles.TileEntityProducerEu;
import net.xalcon.energyconverters.common.tiles.TileEntityTechRebornProducer;
import reborncore.api.power.EnumPowerTier;

public class BlockProducerTechReborn extends BlockConverterTechRebornBase implements ITileEntityProvider
{
	public BlockProducerTechReborn()
	{
		super(Material.IRON, "energy_producer_tr");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityTechRebornProducer(EnumPowerTier.values()[meta]);
	}
}
