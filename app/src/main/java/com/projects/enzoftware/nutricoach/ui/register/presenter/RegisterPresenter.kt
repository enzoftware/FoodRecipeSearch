package com.projects.enzoftware.nutricoach.ui.register.presenter

import com.projects.enzoftware.nutricoach.network.response.RegisterResponse
import com.projects.enzoftware.nutricoach.repository.NutriCoachRepository
import com.projects.enzoftware.nutricoach.repository.RepositoryCallback
import com.projects.enzoftware.nutricoach.ui.base.BasePresenter
import com.projects.enzoftware.nutricoach.ui.register.view.RegisterView

class RegisterPresenter(private val repository: NutriCoachRepository) :
    BasePresenter<RegisterView?>() {

    fun register(firstName: String, lastName: String, email: String, password: String){
        repository.register(
            email,
            password,
            firstName,
            lastName,
            object : RepositoryCallback<RegisterResponse> {
                override fun onSuccess(t: RegisterResponse?) {
                    if (t != null) view?.onRegisterSuccess()
                }

                override fun onError() {
                    view?.onRegisterFail()
                }

            })
    }
}