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
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.xalcon.energyconverters.EnergyConverters;
import reborncore.api.power.EnumPowerTier;

import java.util.List;

public abstract class BlockConverterTechRebornBase extends BlockBase
{
	public enum PowerTierMap implements IStringSerializable
	{
		LOW(EnumPowerTier.LOW, "low", 0),
		MEDIUM(EnumPowerTier.MEDIUM, "medium", 1),
		HIGH(EnumPowerTier.HIGH, "high", 2),
		EXTREME(EnumPowerTier.EXTREME, "extreme", 3),
		INSANE(EnumPowerTier.INSANE, "insane", 4),
		INFINITE(EnumPowerTier.INFINITE, "infinite", 5);
		private EnumPowerTier tier;
		private String name;
		private int meta;

		PowerTierMap(EnumPowerTier tier, String name, int meta)
		{
			this.tier = tier;
			this.name = name;
			this.meta = meta;
		}

		public EnumPowerTier getTier()
		{
			return tier;
		}

		public String getName()
		{
			return name;
		}

		public int getMeta()
		{
			return meta;
		}
	}

	public static final PropertyEnum<PowerTierMap> TYPE = PropertyEnum.create("tier", PowerTierMap.class);

	public BlockConverterTechRebornBase(Material material, String name)
	{
		super(material, name);
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
	{
		for (PowerTierMap t : PowerTierMap.values())
			list.add(new ItemStack(itemIn, 1, t.getMeta()));
	}

	@Override
	public void registerItemModel(ItemBlock itemBlock)
	{
		for (PowerTierMap t : PowerTierMap.values())
			EnergyConverters.Proxy.registerItemRenderer(itemBlock, t.ordinal(), this.internalName, "tier=" + t.getName());
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return getMetaFromState(state);
	}

	@Override
	@SuppressWarnings("deprecation")
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(TYPE, PowerTierMap.values()[meta]);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(TYPE).getMeta();
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, TYPE);
	}
}
