package com.projects.enzoftware.nutricoach.model

data class Recipe (val recipeId : String,
                   val title : String,
                   val imageUrl : String?,
                   val sourceUrl : String?,
                   val isFavorite : Boolean)