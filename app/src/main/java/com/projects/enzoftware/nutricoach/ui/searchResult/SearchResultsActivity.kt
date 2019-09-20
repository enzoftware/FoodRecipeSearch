package com.projects.enzoftware.nutricoach.ui.searchResult

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.projects.enzoftware.nutricoach.R
import com.projects.enzoftware.nutricoach.model.Recipe
import com.projects.enzoftware.nutricoach.repository.RecipeRepositoryImpl
import com.projects.enzoftware.nutricoach.ui.searchResult.adapter.Listener
import com.projects.enzoftware.nutricoach.ui.searchResult.adapter.RecipeAdapter
import com.projects.enzoftware.nutricoach.ui.base.ChildActivity
import com.projects.enzoftware.nutricoach.ui.recipe.recipeIntent
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

class SearchResultsActivity : ChildActivity(), SearchResultsPresenter.View{


    // private val repository: RecipeRepository by lazy {RecipeRepository.getRepository(this)}
    private val presenter : SearchResultsPresenter by lazy {
        SearchResultsPresenter(
            RecipeRepositoryImpl.getRepository(this)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_list_recipes)

        val query = intent.getStringExtra(EXTRA_QUERY)
        supportActionBar?.subtitle = query

        presenter.attachView(this)

        presenter.search(query)
        retry.setOnClickListener { presenter.search(query) }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }


    override fun showEmptyRecipes() {
        loadingContainer.visibility = View.GONE
        errorContainer.visibility = View.GONE
        list.visibility = View.VISIBLE
        noresultsContainer.visibility = View.VISIBLE
    }

    override fun showRecipes(recipes: List<Recipe>) {
        loadingContainer.visibility = View.GONE
        errorContainer.visibility = View.GONE
        list.visibility = View.VISIBLE
        noresultsContainer.visibility = View.GONE

        setupRecipeList(recipes)
    }

    override fun showLoading() {
        loadingContainer.visibility = View.VISIBLE
        errorContainer.visibility = View.GONE
        list.visibility = View.GONE
        noresultsContainer.visibility = View.GONE
    }

    override fun showError() {
        loadingContainer.visibility = View.GONE
        errorContainer.visibility = View.VISIBLE
        list.visibility = View.GONE
        noresultsContainer.visibility = View.GONE
    }

    override fun refreshFavoriteStatus(recipeIndex: Int) {
        list.adapter?.notifyDataSetChanged()
    }

    private fun setupRecipeList(recipes: List<Recipe>){
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = RecipeAdapter(recipes, object : Listener{
            override fun onClickItem(item: Recipe) {
                startActivity(recipeIntent(item.sourceUrl))
            }

            override fun onAddFavorite(item: Recipe) {
                presenter.addFavorites(item)
            }

            override fun onRemoveFavorite(item: Recipe) {
                presenter.removeFavorites(item)
            }
        })
    }

}
