package net.xalcon.energyconverters.common.blocks;

import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.xalcon.energyconverters.EnergyConvertersMod;
import net.xalcon.energyconverters.common.tiles.TileEntityConsumerEu;

import java.util.List;

public class BlockConsumerEu extends BlockBase implements ITileEntityProvider
{
	public enum EnumTypeVoltage implements IStringSerializable
	{
		LowVoltage(0, 1, "lv"),
		MediumVoltage(1, 2, "mv"),
		HighVoltage(2, 3, "hv"),
		ExtremeVoltage(3, 4, "ev");

		private int meta;
		private int tier;
		private String name;

		EnumTypeVoltage(int meta, int tier, String name)
		{
			this.meta = meta;
			this.tier = tier;
			this.name = name;
		}

		public int getMeta()
		{
			return meta;
		}

		public int getTier()
		{
			return tier;
		}

		@Override
		public String getName()
		{
			return name;
		}
	}

	public static final PropertyEnum TYPE = PropertyEnum.create("tier", EnumTypeVoltage.class);

	public BlockConsumerEu()
	{
		super(Material.IRON, "energy_consumer_eu");
		//this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumTypeVoltage.LowVoltage));
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
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
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumTypeVoltage) state.getValue(TYPE)).getMeta();
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, TYPE);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityConsumerEu(meta + 1);
	}
}
