package net.xalcon.energyconverters.common.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.xalcon.energyconverters.EnergyConverters;
import net.xalcon.energyconverters.common.init.ModBlocks;

import java.lang.ref.WeakReference;

public abstract class TileEntityConverterBase extends TileEntity
{
	private WeakReference<TileEntityEnergyBridge> cachedEnergyBridge;

	public TileEntityConverterBase()
	{
		this.cachedEnergyBridge = new WeakReference<>(null);
	}

	TileEntityEnergyBridge getEnergyBridge()
	{
		TileEntityEnergyBridge energyBridge = this.cachedEnergyBridge.get();
		if(energyBridge == null || energyBridge.isInvalid())
		{
			energyBridge = null;
			for(EnumFacing direction : EnumFacing.VALUES)
			{
				BlockPos pos = this.pos.offset(direction);
				IBlockState blockState = this.getWorld().getBlockState(pos);
				if(blockState.getBlock() != ModBlocks.getEnergyBridge()) continue;
				TileEntity te = this.getWorld().getTileEntity(pos);
				if(te == null || !(te instanceof TileEntityEnergyBridge))
				{
					EnergyConverters.Log.error("Expected TileEntityEnergyBridge @ " + pos + " but found " + te + ". Try replacing the affected block");
					continue;
				}
				if(te.isInvalid()) continue;
				energyBridge = (TileEntityEnergyBridge) te;
				this.cachedEnergyBridge = new WeakReference<>(energyBridge);
			}
		}
		return energyBridge;
	}
}
