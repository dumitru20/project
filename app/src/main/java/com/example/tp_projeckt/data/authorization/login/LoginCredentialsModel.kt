package com.example.tp_projeckt.data.authorization.login

import com.squareup.moshi.Json

data class LoginCredentialsModel(
	@Json(name = "login") val login: String,
	@Json(name = "password") val password: String
)
