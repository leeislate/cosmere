/*
 * File updated ~ 19 - 11 - 2023 ~ Leaf
 */

package leaf.cosmere.example;

import leaf.cosmere.api.helpers.ResourceLocationHelper;
import leaf.cosmere.example.common.Example;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ExampleRecipeGen extends RecipeProvider implements IConditionBuilder
{
	public ExampleRecipeGen(DataGenerator generatorIn)
	{
		super(generatorIn);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer)
	{
	}


	protected static void addOreSmeltingRecipes(Consumer<FinishedRecipe> consumer, ItemLike ore, Item result, float experience, int time)
	{
		String name = ResourceLocationHelper.get(result).getPath();
		String path = ResourceLocationHelper.get(ore.asItem()).getPath();
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ore), result, experience, time).unlockedBy("has_ore", has(ore)).save(consumer, Example.rl(name + "_from_smelting_" + path));
		SimpleCookingRecipeBuilder.blasting(Ingredient.of(ore), result, experience, time / 2).unlockedBy("has_ore", has(ore)).save(consumer, Example.rl(name + "_from_blasting_" + path));
	}

}
