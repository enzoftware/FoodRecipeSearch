package com.projects.enzoftware.nutricoach.ui.search

import com.projects.enzoftware.nutricoach.ui.base.BasePresenter


class SearchPresenter : BasePresenter<SearchPresenter.View?>(){

    fun search(query : String){
        if (query.trim().isBlank()){
            view?.showQueryRequiredMessage()
        }else{
            view?.showSearchResults(query)
        }
    }

    interface View{
        fun showQueryRequiredMessage()
        fun showSearchResults(query: String)
    }
}