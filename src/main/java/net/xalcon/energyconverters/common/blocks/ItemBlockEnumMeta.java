package net.xalcon.energyconverters.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public class ItemBlockEnumMeta<T extends Enum<T> & IStringSerializable> extends ItemBlock
{
	private T[] values;

	public ItemBlockEnumMeta(Block block, T[] values)
	{
		super(block);
		this.setHasSubtypes(true);
		this.values = values;
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}

	@Override
	public String getTranslationKey(ItemStack stack)
	{
		return super.getTranslationKey(stack) + "." + this.values[stack.getMetadata()].getName();
	}
}
