package com.example.tp_projeckt.data.login.authorization

import com.example.tp_projeckt.data.token.TokenModel
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginApi {

	@POST("/api/login")
	suspend fun authentication(@Body loginCredentials: LoginCredentialsModel): TokenModel


}