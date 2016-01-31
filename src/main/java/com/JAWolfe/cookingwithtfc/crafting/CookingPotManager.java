package com.JAWolfe.cookingwithtfc.crafting;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraftforge.fluids.FluidStack;

public class CookingPotManager 
{
	private static final CookingPotManager INSTANCE = new CookingPotManager();
	public static final CookingPotManager getInstance()
	{
		return INSTANCE;
	}
	
	private List<CookingPotRecipe> recipes;
	
	public CookingPotManager()
	{
		recipes = new ArrayList<CookingPotRecipe>();
	}
	
	public void addRecipe(CookingPotRecipe recipe)
	{
		recipes.add(recipe);
	}
	
	public void clearRecipes()
	{
		recipes.clear();
	}
	
	public CookingPotRecipe findMatchingRecipe(FluidStack inputFluid, Item[] inputIngreds)
	{
		for (int i = 0; i < recipes.size(); i++)
		{
			CookingPotRecipe irecipe = recipes.get(i);
			if (irecipe != null && irecipe.matches(inputFluid, inputIngreds))
				return irecipe.getRecipe();
		}
		return null;
	}
}