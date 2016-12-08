package net.xalcon.energyconverters.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.xalcon.energyconverters.EnergyConvertersMod;
import net.xalcon.energyconverters.common.tiles.TileEntityConsumerEu;

import java.util.List;

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
