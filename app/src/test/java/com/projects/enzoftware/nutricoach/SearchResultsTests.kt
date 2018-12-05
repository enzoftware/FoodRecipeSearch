package com.projects.enzoftware.nutricoach

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.projects.enzoftware.nutricoach.repository.RecipeRepository
import com.projects.enzoftware.nutricoach.ui.presenter.SearchResultsPresenter
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
}