package com.projects.enzoftware.nutricoach.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.projects.enzoftware.nutricoach.R
import de.mrapp.android.dialog.ProgressDialog

abstract class BaseActivity : AppCompatActivity(){

    private lateinit var progressDialog: ProgressDialog

    fun showLoadingDialog() {
        val dialogBuilder = ProgressDialog.Builder(this)
        dialogBuilder.setTitle(getString(R.string.please_wait_txt))
        dialogBuilder.setMessage(getString(R.string.loading_txt))
        dialogBuilder.setProgressBarPosition(ProgressDialog.ProgressBarPosition.LEFT)
        progressDialog = dialogBuilder.create()
        progressDialog.show()
    }

    fun hideLoadingDialog() {
        progressDialog.hide()
    }
}