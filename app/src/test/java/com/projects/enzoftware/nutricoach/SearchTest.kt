package com.projects.enzoftware.nutricoach

import android.view.View
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import com.projects.enzoftware.nutricoach.ui.presenter.SearchPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class SearchTest {

    private lateinit var presenter: SearchPresenter
    private lateinit var view: SearchPresenter.View

    @Before
    fun setup(){
        presenter = SearchPresenter()
        view = mock()
        presenter.attachView(view)
    }

    @Test
    fun search_withEmptyQuery_callShowQueryRequiredMessage() {
        presenter.search("")
        verify(view).showQueryRequiredMessage()
    }

    @Test
    fun search_withEmptyQuery_doesNotCallsShowSearchResult(){
        presenter.search("")
        verify(view, never()).showSearchResults(anyString())
    }

}