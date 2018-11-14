package com.projects.enzoftware.nutricoach.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.projects.enzoftware.nutricoach.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        searchButton.setOnClickListener {
            val query = ingredients.text.toString().trim()
            if (query.isBlank()){
                Snackbar.make(searchButton, "Please, input one or more ingredients", Snackbar.LENGTH_SHORT)
                        .show()
            }else{
                startActivity(searchResultsIntent(query))
            }

        }
    }
}
