package com.projects.enzoftware.nutricoach.ui.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.projects.enzoftware.nutricoach.R
import com.projects.enzoftware.nutricoach.ui.register.view.RegisterView

class RegisterActivity : AppCompatActivity(), RegisterView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
    }


    override fun onRegisterSuccess() {

    }

    override fun onRegisterFail() {

    }


}
