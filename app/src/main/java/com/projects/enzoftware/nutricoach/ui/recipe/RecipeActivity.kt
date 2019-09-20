package com.projects.enzoftware.nutricoach.ui.recipe

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.projects.enzoftware.nutricoach.R
import kotlinx.android.synthetic.main.activity_recipe.*


private const val EXTRA_URL = "EXTRA_URL"

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val url = intent.getStringExtra(EXTRA_URL)
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.apply {
            isHorizontalScrollBarEnabled = true
            loadUrl(url)
            webViewClient = object : WebViewClient(){}
        }
    }
}

fun Context.recipeIntent(url: String?): Intent{
    return Intent(this, RecipeActivity::class.java).apply {
        putExtra(EXTRA_URL, url)
    }
}
