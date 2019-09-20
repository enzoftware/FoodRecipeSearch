package com.projects.enzoftware.nutricoach.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class ChildActivity : AppCompatActivity() {

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}