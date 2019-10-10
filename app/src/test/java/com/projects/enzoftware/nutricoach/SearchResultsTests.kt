package com.projects.enzoftware.nutricoach

import com.nhaarman.mockito_kotlin.*
import com.projects.enzoftware.nutricoach.model.Recipe
import com.projects.enzoftware.nutricoach.repository.RecipeRepository
import com.projects.enzoftware.nutricoach.repository.RepositoryCallback
import com.projects.enzoftware.nutricoach.ui.searchResult.SearchResultsPresenter
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SearchResultsTests {

    private lateinit var repository : RecipeRepository
    private lateinit var presenter: SearchResultsPresenter
    private lateinit var view : SearchResultsPresenter.View


    @Before
    fun setup(){
        repository = mock()
        view = mock()
        presenter = SearchResultsPresenter(repository)
        presenter.attachView(view)
    }

    @Test
    fun search_callShowLoading(){
        presenter.search("eggs")
        verify(view).showLoading()
    }

    @Test
    fun search_callGetRecipes(){
        presenter.search("eggs")
        verify(repository).getRecipes(eq("eggs"), any())
    }

    @Test
    fun search_withRepositoryHavingRecipes_callsShowRecipes() {

        val recipe = Recipe("id", "title", "imageUrl", "sourceUrl", false)
        val recipes = listOf<Recipe>(recipe)

        doAnswer {
            val callback : RepositoryCallback<List<Recipe>> = it.getArgument(1)
            callback.onSuccess(recipes)
        }.whenever(repository).getRecipes(eq("eggs"), any())

        presenter.search("eggs")
        verify(view).showRecipes(eq(recipes))
    }

    @Test
    fun addFavorites_shouldUpdateRecipesStatus() {
        val recipe = Recipe("id", "title", "imageUrl", "sourceUrl", false)
        presenter.addFavorites(recipe)
        Assert.assertTrue(recipe.isFavorite)
    }

}