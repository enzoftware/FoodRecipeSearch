package com.projects.enzoftware.nutricoach.ui.login


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.projects.enzoftware.nutricoach.R
import com.projects.enzoftware.nutricoach.ui.login.view.LoginView

class LoginActivity : AppCompatActivity(), LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
    }

    override fun onLoginSuccess() {

    }

    override fun onLoginFail() {

    }

}
