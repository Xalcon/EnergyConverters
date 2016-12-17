package net.xalcon.energyconverters.common.blocks;

import ic2.api.item.IC2Items;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.xalcon.energyconverters.common.tiles.TileEntityProducerEu;

import javax.annotation.Nullable;
import java.util.List;

public class BlockProducerEu extends BlockConverterEuBase implements ITileEntityProvider
{
	public BlockProducerEu()
	{
		super(Material.IRON, "energy_producer_eu");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityProducerEu(meta + 1);
	}
}
