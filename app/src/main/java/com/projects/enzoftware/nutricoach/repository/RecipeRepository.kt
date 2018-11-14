package com.projects.enzoftware.nutricoach.repository

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.projects.enzoftware.nutricoach.api.RecipeApi
import com.projects.enzoftware.nutricoach.model.Recipe
import com.projects.enzoftware.nutricoach.model.RecipesContainer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val FAVORITES_KEY = "FAVORITES_KEY"

class RecipeRepository(private val sharedPreferences: SharedPreferences) {

    private val gson = Gson()

    fun addFavorite(item: Recipe) {
        val favorites = getFavoriteRecipes() + item
        saveFavorites(favorites)
    }

    fun removeFavorite(item: Recipe) {
        val favorites = getFavoriteRecipes() - item
        saveFavorites(favorites)
    }

    private fun saveFavorites(favorites: List<Recipe>) {
        val editor = sharedPreferences.edit()
        editor.putString(FAVORITES_KEY, gson.toJson(favorites))
        editor.apply()
    }

    private inline fun <reified T> Gson.fromJson(json: String): T = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

    fun getFavoriteRecipes(): List<Recipe> {
        val favoritesString = sharedPreferences.getString(FAVORITES_KEY, null)
        if (favoritesString != null) {
            return gson.fromJson(favoritesString)
        }

        return emptyList()
    }

    fun getRecipes(query: String, callback: RepositoryCallback<List<Recipe>>) {
        val call = RecipeApi.create().search(query)
        call.enqueue(object : Callback<RecipesContainer> {
            override fun onResponse(call: Call<RecipesContainer>?, response: Response<RecipesContainer>?) {
                if (response != null && response.isSuccessful) {
                    val recipesContainer = response.body()
                    markFavorites(recipesContainer)
                    callback.onSuccess(recipesContainer?.recipes)
                } else {
                    callback.onError()
                }
            }

            override fun onFailure(call: Call<RecipesContainer>?, t: Throwable?) {
                callback.onError()
            }
        })
    }

    private fun markFavorites(recipesContainer: RecipesContainer?) {
        if (recipesContainer != null) {
            val favoriteRecipes = getFavoriteRecipes()
            if (favoriteRecipes.isNotEmpty()) {
                for (item in recipesContainer.recipes) {
                    item.isFavorite = favoriteRecipes.map { it.recipeId }.contains(item.recipeId)
                }
            }
        }
    }

    interface RepositoryCallback<in T> {
        fun onSuccess(t: T?)
        fun onError()
    }

    companion object {
        fun getRepository(context: Context): RecipeRepository {
            return RecipeRepository(context.getSharedPreferences("Favorites", Context.MODE_PRIVATE))
        }
    }
}