package com.projects.enzoftware.nutricoach.ui.login.presenter

import android.util.Log
import com.projects.enzoftware.nutricoach.network.response.LoginResponse
import com.projects.enzoftware.nutricoach.repository.NutriCoachRepository
import com.projects.enzoftware.nutricoach.repository.RepositoryCallback
import com.projects.enzoftware.nutricoach.ui.base.BasePresenter
import com.projects.enzoftware.nutricoach.ui.login.view.LoginView

class LoginPresenter(private val repository: NutriCoachRepository) : BasePresenter<LoginView?>() {

    fun login(email: String, password: String){
        repository.login(email, password, object : RepositoryCallback<LoginResponse> {
            override fun onSuccess(t: LoginResponse?) {
                Log.d("LOGIN RESPONSE", t.toString())
                if (t != null && t.errors.isEmpty()) view?.onLoginSuccess()
                else view?.onLoginFail()
            }
            override fun onError() {
                view?.onLoginFail()
            }
        })
    }

}