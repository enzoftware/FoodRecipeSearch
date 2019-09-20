package com.projects.enzoftware.nutricoach.repository

import com.projects.enzoftware.nutricoach.network.response.LoginResponse

/**
 * This repository is for testing porpoise
 *
 */
class NutriCoachRepositoryFakeImpl : NutriCoachRepository{
    override fun login(email: String, password: String, callback: NutriCoachCallback<LoginResponse>) {

    }

    override fun register(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        callback: NutriCoachCallback<String>
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}