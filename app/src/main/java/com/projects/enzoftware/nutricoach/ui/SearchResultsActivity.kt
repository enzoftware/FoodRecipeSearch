package com.projects.enzoftware.nutricoach.ui

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.projects.enzoftware.nutricoach.R
import com.projects.enzoftware.nutricoach.model.Recipe
import com.projects.enzoftware.nutricoach.repository.RecipeRepository
import com.projects.enzoftware.nutricoach.ui.adapter.Listener
import com.projects.enzoftware.nutricoach.ui.adapter.RecipeAdapter
import kotlinx.android.synthetic.main.view_error.*
import kotlinx.android.synthetic.main.view_list_recipes.*
import kotlinx.android.synthetic.main.view_loading.*
import kotlinx.android.synthetic.main.view_noresults.*

fun Context.searchResultsIntent(query: String): Intent {
    return Intent(this, SearchResultsActivity::class.java).apply {
        putExtra(EXTRA_QUERY, query)
    }
}

private const val EXTRA_QUERY = "EXTRA_QUERY"

class SearchResultsActivity : ChildActivity() {

    private val repository: RecipeRepository by lazy {RecipeRepository.getRepository(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_list_recipes)

        val query = intent.getStringExtra(EXTRA_QUERY)
        supportActionBar?.subtitle = query

        search(query)

        retry.setOnClickListener { search(query) }
    }

    private fun search(query: String) {
        showLoadingView()
        repository.getRecipes(query, object : RecipeRepository.RepositoryCallback<List<Recipe>> {
            override fun onSuccess(recipes: List<Recipe>?) {
                if (recipes != null && recipes.isNotEmpty()) {
                    showRecipes(recipes)
                } else {
                    showEmptyRecipes()
                }
            }

            override fun onError() {
                showErrorView()
            }
        })
    }

    private fun showEmptyRecipes() {
        loadingContainer.visibility = View.GONE
        errorContainer.visibility = View.GONE
        list.visibility = View.VISIBLE
        noresultsContainer.visibility = View.VISIBLE
    }

    private fun showRecipes(recipes: List<Recipe>) {
        loadingContainer.visibility = View.GONE
        errorContainer.visibility = View.GONE
        list.visibility = View.VISIBLE
        noresultsContainer.visibility = View.GONE

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = RecipeAdapter(recipes, object : Listener {
            override fun onClickItem(item: Recipe) {
                startActivity(recipeIntent(item.sourceUrl!!))
            }

            override fun onAddFavorite(item: Recipe) {
                item.isFavorite = true
                repository.addFavorite(item)
                list.adapter!!.notifyItemChanged(recipes.indexOf(item))
            }

            override fun onRemoveFavorite(item: Recipe) {
                repository.removeFavorite(item)
                item.isFavorite = false
                list.adapter!!.notifyItemChanged(recipes.indexOf(item))
            }

        })
    }

    private fun showErrorView() {
        loadingContainer.visibility = View.GONE
        errorContainer.visibility = View.VISIBLE
        list.visibility = View.GONE
        noresultsContainer.visibility = View.GONE
    }

    private fun showLoadingView() {
        loadingContainer.visibility = View.VISIBLE
        errorContainer.visibility = View.GONE
        list.visibility = View.GONE
        noresultsContainer.visibility = View.GONE
    }
}
