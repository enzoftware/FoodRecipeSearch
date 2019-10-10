package com.projects.enzoftware.nutricoach.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.projects.enzoftware.nutricoach.network.request.LoginRequest
import com.projects.enzoftware.nutricoach.network.request.RegisterRequest
import com.projects.enzoftware.nutricoach.network.response.LoginResponse
import com.projects.enzoftware.nutricoach.network.response.RegisterResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface NutriCoachApi {

    @POST("/auth")
    fun login(@Body body: LoginRequest): Call<LoginResponse>

    @POST("/users")
    fun registerUser(@Body body : RegisterRequest) : Call<RegisterResponse>

    companion object Factory {
        fun create(): NutriCoachApi {
            val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://nutricoach-api-express.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(NutriCoachApi::class.java)
        }
    }
}