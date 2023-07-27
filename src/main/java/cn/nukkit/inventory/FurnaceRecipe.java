package cn.nukkit.inventory;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.item.Item;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author MagicDroidX (Nukkit Project)
 */
public class FurnaceRecipe implements SmeltingRecipe {
    private final Item output;
    private Item ingredient;
    private String recipeId;

    private double experience;

    public FurnaceRecipe(Item result, Item ingredient) {
        this(null, result, ingredient);
    }

    @PowerNukkitXOnly
    public FurnaceRecipe(@Nullable String recipeId, Item result, Item ingredient) {
        this(result, ingredient, 0);
        this.recipeId = recipeId == null ? CraftingManager.getMultiItemHash(List.of(ingredient, result)).toString() : recipeId;
    }

    public FurnaceRecipe(Item result, Item ingredient, double experience) {
        this.output = result.clone();
        this.ingredient = ingredient.clone();
        this.experience = experience;
    }

    @Override
    public String getRecipeId() {
        return recipeId;
    }

    public void setInput(Item item) {
        this.ingredient = item.clone();
    }

    @Override
    public Item getInput() {
        return this.ingredient.clone();
    }

    @Override
    public Item getResult() {
        return this.output.clone();
    }

    public double getExperience() {
        return experience;
    }

    @Override
    public void registerToCraftingManager(CraftingManager manager) {
        manager.registerFurnaceRecipe(this);
    }

    @Override
    public RecipeType getType() {
        return this.ingredient.hasMeta() ? RecipeType.FURNACE_DATA : RecipeType.FURNACE;
    }
}
