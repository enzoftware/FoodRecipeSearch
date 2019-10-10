package com.projects.enzoftware.nutricoach.ui.login


import android.content.Intent
import android.os.Bundle
import com.projects.enzoftware.nutricoach.R
import com.projects.enzoftware.nutricoach.repository.NutriCoachRepositoryImpl
import com.projects.enzoftware.nutricoach.ui.base.BaseActivity
import com.projects.enzoftware.nutricoach.ui.commons.showGeneralError
import com.projects.enzoftware.nutricoach.ui.home.HomeActivity
import com.projects.enzoftware.nutricoach.ui.login.presenter.LoginPresenter
import com.projects.enzoftware.nutricoach.ui.login.view.LoginView
import com.projects.enzoftware.nutricoach.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.layout_login.*

class LoginActivity : BaseActivity(), LoginView {

    private val presenter: LoginPresenter by lazy {
        LoginPresenter(
            NutriCoachRepositoryImpl.getRepository(
                this
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        presenter.attachView(this)
        initUi()
    }

    private fun initUi() {
        login_btn.setOnClickListener {
            showLoadingDialog()
            val email = login_email.text.trim().toString()
            val pass = login_pass.text.trim().toString()
            presenter.login(email, pass)
        }
        login_go_to_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun onLoginSuccess() {
        hideLoadingDialog()
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun onLoginFail() {
        hideLoadingDialog()
        showGeneralError(this)
    }

}
