package com.projects.enzoftware.nutricoach.ui.presenter

abstract class BasePresenter<V> {

    protected var view : V? = null

    fun attachView(view : V){
        this.view = view
    }

    fun detachView(view: V){
        this.view = null
    }

}