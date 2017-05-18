package net.xalcon.energyconverters.common.init;

import ic2.api.item.IC2Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.xalcon.energyconverters.common.blocks.EnumTypeVoltage;

public class ModRecipes
{
	public static void init()
	{
		initBasic();
		initTesla();
		initIC2();
	}

	public static void initBasic()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(ModBlocks.EnergyBridge),
				"IBI", "BEB", "IBI",
				'I', "ingotIron",
				'B', Item.REGISTRY.getObject(new ResourceLocation("minecraft", "iron_bars")),
				'E', Item.REGISTRY.getObject(new ResourceLocation("minecraft", "ender_eye"))));

		if(Loader.isModLoaded("EnderIO"))
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(ModBlocks.ProducerRf),
					"SCS", "PMG", "SDS",
					'S', "stone",
					'C', Item.REGISTRY.getObject(new ResourceLocation("enderio", "itemPowerConduit")),
					'P', Item.REGISTRY.getObject(new ResourceLocation("enderio", "blockStirlingGenerator")),
					'M', Item.REGISTRY.getObject(new ResourceLocation("enderio", "itemMachinePart")),
					'G', "ingotGold",
					'D', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("enderio", "itemBasicCapacitor")), 1, 1)));
		}
		else
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(ModBlocks.ProducerRf),
					"SIS", "FBG", "SbS",
					'S', "stone",
					'I', "ingotIron",
					'F', Item.REGISTRY.getObject(new ResourceLocation("minecraft", "furnace")),
					'B', "blockIron",
					'G', "ingotGold",
					'b', Item.REGISTRY.getObject(new ResourceLocation("minecraft", "iron_bars"))));
		}


		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(ModBlocks.ProducerFe),
				"SIS", "FBG", "SbS",
				'S', "stone",
				'I', "ingotIron",
				'F', Item.REGISTRY.getObject(new ResourceLocation("minecraft", "furnace")),
				'B', "blockRedstone",
				'G', "ingotGold",
				'b', Item.REGISTRY.getObject(new ResourceLocation("minecraft", "iron_bars"))));

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.ConsumerRf), ModBlocks.ProducerRf);
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.ProducerRf), ModBlocks.ConsumerRf);
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.ConsumerFe), ModBlocks.ProducerFe);
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.ProducerFe), ModBlocks.ConsumerFe);
	}

	private static void initIC2()
	{
		if(Loader.isModLoaded("ic2"))
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(ModBlocks.ProducerEu, 1, 0),
					"SCS", "TMG", "ScS",
					'S', "stone",
					'C', IC2Items.getItem("cable", "type:tin,insulation:1"),
					'T', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "te")), 1, 77),
					'M', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "resource")), 1, 12),
					'G', "ingotGold",
					'c', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "crafting")), 1, 5)));


			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(ModBlocks.ProducerEu, 1, 1),
					"SCS", "TMG", "ScS",
					'S', "stone",
					'C', IC2Items.getItem("cable", "type:copper,insulation:1"),
					'T', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "te")), 1, 78),
					'M', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "resource")), 1, 12),
					'G', "ingotGold",
					'c', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "crafting")), 1, 5)));

			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(ModBlocks.ProducerEu, 1, 2),
					"SCS", "TMG", "ScS",
					'S', "stone",
					'C', IC2Items.getItem("cable", "type:gold,insulation:2"),
					'T', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "te")), 1, 79),
					'M', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "resource")), 1, 12),
					'G', "ingotGold",
					'c', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "crafting")), 1, 5)));

			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(ModBlocks.ProducerEu, 1, 3),
					"SCS", "TMG", "ScS",
					'S', "stone",
					'C',  IC2Items.getItem("cable", "type:iron,insulation:3"),
					'T', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "te")), 1, 80),
					'M', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "resource")), 1, 12),
					'G', "ingotGold",
					'c', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "crafting")), 1, 5)));

			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(ModBlocks.ProducerEu, 1, 4),
					"SCS", "TMT", "ScS",
					'S', "blockIron",
					'C',  IC2Items.getItem("cable", "type:glass,insulation:0"),
					'T', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "te")), 1, 80),
					'M', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "resource")), 1, 12),
					'c', new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("ic2", "crafting")), 1, 5)));

			for(EnumTypeVoltage t : EnumTypeVoltage.values())
			{
				GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.ConsumerEu, 1, t.getMeta()), new ItemStack(ModBlocks.ProducerEu, 1, t.getMeta()));
				GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.ProducerEu, 1, t.getMeta()), new ItemStack(ModBlocks.ConsumerEu, 1, t.getMeta()));
			}
		}
	}

	private static void initTesla()
	{
		if(Loader.isModLoaded("tesla"))
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(ModBlocks.ProducerTesla),
					"SQS", "FBG", "SbS",
					'S', "stone",
					'Q', "gemQuartz",
					'F', Item.REGISTRY.getObject(new ResourceLocation("minecraft", "furnace")),
					'B', "blockIron",
					'G', "ingotGold",
					'b', Item.REGISTRY.getObject(new ResourceLocation("minecraft", "iron_bars"))));

			GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.ConsumerTesla), ModBlocks.ProducerTesla);
			GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.ProducerTesla), ModBlocks.ConsumerTesla);
		}
	}
}
