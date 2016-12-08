package net.xalcon.energyconverters.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBlockTieredVoltage extends ItemBlock
{
	public ItemBlockTieredVoltage(Block block)
	{
		super(block);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + "." + BlockConsumerEu.EnumTypeVoltage.values()[stack.getMetadata()].getName();
	}


}
