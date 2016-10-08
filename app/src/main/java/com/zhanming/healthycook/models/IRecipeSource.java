package com.zhanming.healthycook.models;

import com.zhanming.healthycook.beans.Recipe;

import java.util.List;

/**
 * Created by zhanming on 2016/10/4.
 */
public interface IRecipeSource {
    boolean addRecipes(List<Recipe> recipes);
    boolean addRecipe(Recipe recipe);
    boolean deleteRecipe(Recipe recipe);
    boolean deleteRecipes();
    List<Recipe> getRecipes();
    Recipe getRecipe(String name);

}
