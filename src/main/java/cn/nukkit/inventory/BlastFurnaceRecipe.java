package cn.nukkit.inventory;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.item.Item;

import javax.annotation.Nullable;
import java.util.List;

@PowerNukkitOnly
public class BlastFurnaceRecipe implements SmeltingRecipe {
    private final Item output;
    private Item ingredient;
    private String recipeId;

    private double experience;

    @PowerNukkitOnly
    public BlastFurnaceRecipe(Item result, Item ingredient) {
        this(null, result, ingredient);
    }

    @PowerNukkitXOnly
    public BlastFurnaceRecipe(@Nullable String recipeId, Item result, Item ingredient) {
        this(result, ingredient, 0);
        this.recipeId = recipeId == null ? CraftingManager.getMultiItemHash(List.of(ingredient, result)).toString() : recipeId;
    }

    public BlastFurnaceRecipe(Item result, Item ingredient, double experience) {
        this.output = result.clone();
        this.ingredient = ingredient.clone();
        this.experience = experience;
    }

    @PowerNukkitOnly
    public void setInput(Item item) {
        this.ingredient = item.clone();
    }

    @PowerNukkitOnly
    @Override
    public Item getInput() {
        return this.ingredient.clone();
    }

    @Override
    public String getRecipeId() {
        return null;
    }

    @Override
    public double getExperience() {
        return experience;
    }

    @Override
    public Item getResult() {
        return this.output.clone();
    }

    @Override
    public void registerToCraftingManager(CraftingManager manager) {
        manager.registerBlastFurnaceRecipe(this);
    }

    @Override
    public RecipeType getType() {
        return this.ingredient.hasMeta() ? RecipeType.BLAST_FURNACE_DATA : RecipeType.BLAST_FURNACE;
    }
}
