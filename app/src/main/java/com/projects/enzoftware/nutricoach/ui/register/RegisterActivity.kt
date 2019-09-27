package com.projects.enzoftware.nutricoach.ui.register

import android.content.Intent
import android.os.Bundle
import com.projects.enzoftware.nutricoach.R
import com.projects.enzoftware.nutricoach.repository.NutriCoachRepositoryImpl
import com.projects.enzoftware.nutricoach.ui.base.BaseActivity
import com.projects.enzoftware.nutricoach.ui.commons.isEmailValid
import com.projects.enzoftware.nutricoach.ui.commons.showGeneralError
import com.projects.enzoftware.nutricoach.ui.home.HomeActivity
import com.projects.enzoftware.nutricoach.ui.login.LoginActivity
import com.projects.enzoftware.nutricoach.ui.register.presenter.RegisterPresenter
import com.projects.enzoftware.nutricoach.ui.register.view.RegisterView
import kotlinx.android.synthetic.main.layout_register.*

class RegisterActivity : BaseActivity(), RegisterView {

    private val presenter: RegisterPresenter by lazy {
        RegisterPresenter(
            NutriCoachRepositoryImpl.getRepository(
                this
            )
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
        presenter.attachView(this)
        initUi()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    private fun initUi() {
        register_btn.setOnClickListener {
            showLoadingDialog()
            val email = register_email.text.trim().toString()
            val password = register_pass.text.trim().toString()
            val name = register_first_name.text.trim().toString()
            val lastName = register_last_name.text.trim().toString()
            if(isEmailValid(email) && password.length >= 6 && name.length > 1 && lastName.length > 1){
                presenter.register(name, lastName, email, password)
            } else {
                onRegisterFail()
            }
        }
        register_go_to_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }


    override fun onRegisterSuccess() {
        hideLoadingDialog()
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun onRegisterFail() {
        hideLoadingDialog()
        showGeneralError(this)
    }

}
