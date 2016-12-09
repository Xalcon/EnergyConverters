package net.xalcon.energyconverters.common.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModRecipes
{
	public static void init()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(ModBlocks.EnergyBridge),
				"IBI", "BEB", "IBI",
				'I', "ingotIron",
				'B', Item.REGISTRY.getObject(new ResourceLocation("minecraft", "iron_bars")),
				'E', Item.REGISTRY.getObject(new ResourceLocation("minecraft", "ender_eye"))));

		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(ModBlocks.ProducerRf),
				"SCS", "PMG", "SDS",
				'S', "stone",
				'C', Item.REGISTRY.getObject(new ResourceLocation("enderio", "itemPowerConduit")),
				'P', Item.REGISTRY.getObject(new ResourceLocation("enderio", "blockStirlingGenerator")),
				'M', Item.REGISTRY.getObject(new ResourceLocation("enderio", "itemMachinePart")),
				'G', "ingotGold",
				'D', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("enderio", "itemBasicCapacitor")), 1, 1)));

		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(ModBlocks.ProducerEu, 1, 0),
				"SCS", "TMG", "ScS",
				'S', "stone",
				'C', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "cable")), 1, 0),
				'T', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "te")), 1, 77),
				'M', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "resource")), 1, 12),
				'G', "ingotGold",
				'c', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "crafting")), 1, 5)));

		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(ModBlocks.ProducerEu, 1, 1),
				"SCS", "TMG", "ScS",
				'S', "stone",
				'C', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "cable")), 1, 2),
				'T', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "te")), 1, 78),
				'M', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "resource")), 1, 12),
				'G', "ingotGold",
				'c', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "crafting")), 1, 5)));

		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(ModBlocks.ProducerEu, 1, 2),
				"SCS", "TMG", "ScS",
				'S', "stone",
				'C', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "cable")), 1, 1),
				'T', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "te")), 1, 79),
				'M', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "resource")), 1, 12),
				'G', "ingotGold",
				'c', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "crafting")), 1, 5)));

		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(ModBlocks.ProducerEu, 1, 3),
				"SCS", "TMG", "ScS",
				'S', "stone",
				'C', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "cable")), 1, 3),
				'T', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "te")), 1, 80),
				'M', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "resource")), 1, 12),
				'G', "ingotGold",
				'c', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "crafting")), 1, 5)));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.ConsumerRf), ModBlocks.ProducerRf);
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.ProducerRf), ModBlocks.ConsumerRf);

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.ConsumerEu, 1, 0), new ItemStack(ModBlocks.ProducerEu, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.ProducerEu, 1, 0), new ItemStack(ModBlocks.ConsumerEu, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.ConsumerEu, 1, 1), new ItemStack(ModBlocks.ProducerEu, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.ProducerEu, 1, 1), new ItemStack(ModBlocks.ConsumerEu, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.ConsumerEu, 1, 2), new ItemStack(ModBlocks.ProducerEu, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.ProducerEu, 1, 2), new ItemStack(ModBlocks.ConsumerEu, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.ConsumerEu, 1, 3), new ItemStack(ModBlocks.ProducerEu, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.ProducerEu, 1, 3), new ItemStack(ModBlocks.ConsumerEu, 1, 3));
	}
}
