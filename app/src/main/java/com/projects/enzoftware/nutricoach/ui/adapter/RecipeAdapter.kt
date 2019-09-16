package com.projects.enzoftware.nutricoach.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.projects.enzoftware.nutricoach.R
import com.projects.enzoftware.nutricoach.model.Recipe
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeAdapter(private var recipes : List<Recipe>, private val listener: Listener) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val itemView = LayoutInflater.from(p0.context)
            .inflate(R.layout.item_recipe, p0, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) = p0.bind(recipes[p1], listener)

    fun removeItem(item: Recipe) {
        recipes -= item
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bind(item: Recipe, listener: Listener) = with(itemView) {
            Glide.with(context).load(item.imageUrl).into(imageView)
            title.text = item.title

            if (item.isFavorite) {
                favButton.setImageResource(R.drawable.ic_favorite_black_24dp)
            } else {
                favButton.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            }

            setOnClickListener {
                listener.onClickItem(item)
            }

            favButton.setOnClickListener {
                if (item.isFavorite) {
                    listener.onRemoveFavorite(item)
                } else {
                    listener.onAddFavorite(item)
                }
            }
        }
    }

}