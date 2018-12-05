package com.projects.enzoftware.nutricoach.ui.presenter

import com.projects.enzoftware.nutricoach.model.Recipe
import com.projects.enzoftware.nutricoach.repository.RecipeRepository
import com.projects.enzoftware.nutricoach.repository.RepositoryCallback

class SearchResultsPresenter(val repository: RecipeRepository) : BasePresenter<SearchResultsPresenter.View?>(){

    private var recipes : List<Recipe>? = null

    fun search(query : String){
        view?.showLoading()

        repository.getRecipes(query, object : RepositoryCallback<List<Recipe>> {

            override fun onSuccess(t: List<Recipe>?) {
                this@SearchResultsPresenter.recipes = t
                if (t != null && t.isNotEmpty()){
                    view?.showRecipes(t)
                }else{
                    view?.showEmptyRecipes()
                }
            }

            override fun onError() {
                view?.showError()
            }
        })
    }

    fun addFavorites(recipe: Recipe){
        recipe.isFavorite = true
        repository.addFavorite(recipe)

        val recipeIndex = recipes?.indexOf(recipe)
        if (recipeIndex != null){
            view?.refreshFavoriteStatus(recipeIndex)
        }
    }

    fun removeFavorites(recipe: Recipe){
        repository.removeFavorite(recipe)
        recipe.isFavorite = false
        val recipeIndex = recipes?.indexOf(recipe)
        if (recipeIndex != null){
            view?.refreshFavoriteStatus(recipeIndex)
        }
    }


    interface View{
        fun showLoading()
        fun showError()
        fun showRecipes(recipes : List<Recipe>)
        fun showEmptyRecipes()
        fun refreshFavoriteStatus(recipeIndex : Int)
    }
}