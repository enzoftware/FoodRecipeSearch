package com.projects.enzoftware.nutricoach.repository

import com.projects.enzoftware.nutricoach.model.Recipe

interface RecipeRepository {
    fun addFavorite(recipe: Recipe)
    fun removeFavorite(recipe: Recipe)
    fun getFavoriteRecipes() : List<Recipe>
    fun getRecipes(query: String, callback: RepositoryCallback<List<Recipe>>)
}

interface RepositoryCallback<in T>{
    fun onSuccess(t : T?)
    fun onError()
}