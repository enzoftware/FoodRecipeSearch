package com.projects.enzoftware.nutricoach.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.projects.enzoftware.nutricoach.api.request.LoginRequest
import com.projects.enzoftware.nutricoach.api.response.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface NutriCoachApi {

    @POST("/auth")
    fun login(@Body body: LoginRequest): Call<LoginResponse>

    @POST("/users")
    fun registerUser(@Body body : LoginRequest)

    companion object Factory {
        fun create(): RecipeApi {
            val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://nutricoach-api-express.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(RecipeApi::class.java)
        }
    }
}