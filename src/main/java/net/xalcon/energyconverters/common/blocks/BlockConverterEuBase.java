package net.xalcon.energyconverters.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.xalcon.energyconverters.EnergyConvertersMod;

public abstract class BlockConverterEuBase extends BlockBase
{
	public static final PropertyEnum TYPE = PropertyEnum.create("tier", EnumTypeVoltage.class);

	public BlockConverterEuBase(Material material, String name)
	{
		super(material, name);
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list)
	{
		for (EnumTypeVoltage t : EnumTypeVoltage.values())
			list.add(new ItemStack(itemIn, 1, t.getMeta()));
	}

	@Override
	public void registerItemModel(ItemBlock itemBlock)
	{
		for (EnumTypeVoltage t : EnumTypeVoltage.values())
			EnergyConvertersMod.Proxy.registerItemRenderer(itemBlock, t.getMeta(), this.internalName, "tier=" + t.getName());
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return getMetaFromState(state);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(TYPE, EnumTypeVoltage.values()[meta]);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumTypeVoltage) state.getValue(TYPE)).getMeta();
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, TYPE);
	}
}
