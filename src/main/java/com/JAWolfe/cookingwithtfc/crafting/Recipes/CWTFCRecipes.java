package com.JAWolfe.cookingwithtfc.crafting.Recipes;

import com.JAWolfe.cookingwithtfc.init.CWTFCBlocks;
import com.JAWolfe.cookingwithtfc.init.Items.CWTFCItems;
import com.JAWolfe.cookingwithtfc.util.LogHelper;
import com.bioxx.tfc.Core.Recipes;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCFluids;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Crafting.BarrelAlcoholRecipe;
import com.bioxx.tfc.api.Crafting.BarrelManager;
import com.bioxx.tfc.api.Crafting.CraftingManagerTFC;
import com.bioxx.tfc.api.Crafting.KilnCraftingManager;
import com.bioxx.tfc.api.Crafting.KilnRecipe;
import com.bioxx.tfc.api.Crafting.QuernManager;
import com.bioxx.tfc.api.Crafting.QuernRecipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CWTFCRecipes 
{
	public static void registerRecipes() 
	{		
		Recipes.removeRecipe(new ItemStack(TFCBlocks.nestBox,1));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(CWTFCBlocks.nestBoxCWTFC,1), "S S","PSP","PPP", 'S', new ItemStack(TFCItems.straw,1), 'P', "woodLumber"));
		for(int i = 0; i < Global.WOOD_ALL.length; i++)
		{
			int l = i%16;
			if(i==l)
			{
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(CWTFCBlocks.prepTable, 1, i), "   ", "PPP", "L L", 'P', new ItemStack(TFCItems.singlePlank, 1, i), 'L', new ItemStack(TFCBlocks.woodSupportV, 1, i)));
			}
			else if(i/16 == 1)
			{
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(CWTFCBlocks.prepTable2, 1, 0), "   ", "PPP", "L L", 'P', new ItemStack(TFCItems.singlePlank, 1, i), 'L', new ItemStack(TFCBlocks.woodSupportV2, 1, 0)));
			}
		}
		
		registerBarrelRecipes();
		registerQuernRecipes();
		registerKilnRecipes();
		registerKnappingRecipes();
		CWTFCFoodRecipes.Recipes();
	}
	
	public static void registerBarrelRecipes() 
	{
		BarrelManager barrelmanager = BarrelManager.getInstance();
		
		barrelmanager.addRecipe(new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(CWTFCItems.redAppleCWTFC), 160), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.CIDER, 10000)));
		barrelmanager.addRecipe(new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(CWTFCItems.greenAppleCWTFC), 160), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.CIDER, 10000)));
		barrelmanager.addRecipe(new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(CWTFCItems.potatoCWTFC), 160), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.VODKA, 10000)));
		barrelmanager.addRecipe(new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(CWTFCItems.barleyGroundCWTFC), 160), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.BEER, 10000)));
		barrelmanager.addRecipe(new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(CWTFCItems.cornmealGroundCWTFC), 160), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.CORNWHISKEY, 10000)));
		barrelmanager.addRecipe(new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(CWTFCItems.riceGroundCWTFC), 160), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.SAKE, 10000)));
		barrelmanager.addRecipe(new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(CWTFCItems.ryeGroundCWTFC), 160), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.RYEWHISKEY, 10000)));
		barrelmanager.addRecipe(new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(CWTFCItems.wheatGroundCWTFC), 160), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.WHISKEY, 10000)));
		//barrelmanager.addRecipe(new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(CWTFCItems.sugar), 160), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.RUM, 10000)));
	}
	
	public static void registerQuernRecipes() 
	{
		QuernManager quernmanager = QuernManager.getInstance();
		
		quernmanager.addRecipe(new QuernRecipe(new ItemStack(CWTFCItems.wheatGrainCWTFC, 1), new ItemStack(CWTFCItems.wheatGroundCWTFC, 1)));//Wheat Flour
		quernmanager.addRecipe(new QuernRecipe(new ItemStack(CWTFCItems.ryeGrainCWTFC, 1), new ItemStack(CWTFCItems.ryeGroundCWTFC, 1)));//Rye Flour
		quernmanager.addRecipe(new QuernRecipe(new ItemStack(CWTFCItems.oatGrainCWTFC, 1), new ItemStack(CWTFCItems.oatGroundCWTFC, 1)));//Oat Flour
		quernmanager.addRecipe(new QuernRecipe(new ItemStack(CWTFCItems.barleyGrainCWTFC, 1), new ItemStack(CWTFCItems.barleyGroundCWTFC, 1)));//Barley Flour
		quernmanager.addRecipe(new QuernRecipe(new ItemStack(CWTFCItems.riceGrainCWTFC, 1), new ItemStack(CWTFCItems.riceGroundCWTFC, 1)));//Rice Flour
		quernmanager.addRecipe(new QuernRecipe(new ItemStack(CWTFCItems.maizeEarCWTFC, 1), new ItemStack(CWTFCItems.cornmealGroundCWTFC, 1)));//Cornmeal
	}
	
	public static void registerKilnRecipes()
	{
		KilnCraftingManager kilnmanager = KilnCraftingManager.getInstance();
		
		kilnmanager.addRecipe(new KilnRecipe(new ItemStack(CWTFCBlocks.mixingBowl,1,0), 0, new ItemStack(CWTFCBlocks.mixingBowl,1,1)));
	}
	
	public static void registerKnappingRecipes()
	{
		CraftingManagerTFC craftingmanager = CraftingManagerTFC.getInstance();
		
		craftingmanager.addRecipe(new ItemStack(CWTFCBlocks.mixingBowl, 1, 0), new Object[] { 
				"#####",
				"#####",
				"     ",
				"     ",
				"#   #", '#', new ItemStack(TFCItems.flatClay, 1, 1)});
	}
 }