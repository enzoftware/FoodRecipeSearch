package com.projects.enzoftware.nutricoach.repository

import android.content.Context
import android.util.Log
import com.projects.enzoftware.nutricoach.network.NutriCoachApi
import com.projects.enzoftware.nutricoach.network.request.LoginRequest
import com.projects.enzoftware.nutricoach.network.request.RegisterRequest
import com.projects.enzoftware.nutricoach.network.response.LoginResponse
import com.projects.enzoftware.nutricoach.network.response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NutriCoachRepositoryImpl : NutriCoachRepository {

    override fun login(email: String, password: String, callback: RepositoryCallback<LoginResponse>) {
        val request = NutriCoachApi.create().login(LoginRequest(email, password))
        request.enqueue(object: Callback<LoginResponse>{

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("LOGIN RESPONSE", response.body().toString())
                callback.onSuccess(response.body())
            }
        })

    }

    override fun register(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        callback: RepositoryCallback<RegisterResponse>
    ) {
        val request = NutriCoachApi.create()
            .registerUser(RegisterRequest(firstName, lastName, email, password))
        request.enqueue(object : Callback<RegisterResponse>{

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                callback.onSuccess(response.body())
            }
        })
    }


    companion object {
        fun getRepository(context: Context): NutriCoachRepository {
            return NutriCoachRepositoryImpl()
        }
    }

}