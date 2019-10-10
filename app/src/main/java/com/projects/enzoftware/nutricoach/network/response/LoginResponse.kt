package com.projects.enzoftware.nutricoach.network.response

data class LoginResponse(
    var accessToken: String = "",
    var refreshToken: String = "",
    var userId: String = "",
    var errors: List<String> = emptyList()
)