package com.projects.enzoftware.nutricoach.ui.login


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.projects.enzoftware.nutricoach.R
import com.projects.enzoftware.nutricoach.ui.login.view.LoginView

class LoginActivity : AppCompatActivity(), LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onLoginSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoginFail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
