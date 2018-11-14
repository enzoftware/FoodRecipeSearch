package com.projects.enzoftware.nutricoach.ui.adapter

import com.projects.enzoftware.nutricoach.model.Recipe

interface Listener {
    fun onClickItem(item: Recipe)
    fun onAddFavorite(item: Recipe)
    fun onRemoveFavorite(item: Recipe)
}