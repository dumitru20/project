package com.example.tp_projeckt.data.authorization.login

import com.example.tp_projeckt.data.token.TokenModel

class LoginDataSourceImpl(private val service: LoginApi) : LoginDataSource {

	override suspend fun authentication(loginCredentials: LoginCredentialsModel): TokenModel {
		return service.authentication(loginCredentials)
	}
}