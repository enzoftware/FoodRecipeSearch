package com.projects.enzoftware.nutricoach.ui

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.projects.enzoftware.nutricoach.R

private const val EXTRA_QUERY = "EXTRA_QUERY"

class SearchResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_list_recipes)
    }
}

fun Context.searchResultsIntent(query: String): Intent {
    return Intent(this, SearchResultsActivity::class.java).apply {
        putExtra(EXTRA_QUERY, query)
    }
}
