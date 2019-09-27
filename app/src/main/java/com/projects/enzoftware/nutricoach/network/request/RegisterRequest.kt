package com.projects.enzoftware.nutricoach.network.request

data class RegisterRequest(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = ""
)