package com.projects.enzoftware.nutricoach.ui.searchResult.adapter

import com.projects.enzoftware.nutricoach.model.Recipe

interface Listener {
    fun onClickItem(item: Recipe)
    fun onAddFavorite(item: Recipe)
    fun onRemoveFavorite(item: Recipe)
}