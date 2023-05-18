package com.example.tp_projeckt.data.login.authorization

import com.example.tp_projeckt.data.token.TokenModel

interface LoginDataSource {

	suspend fun authentication(loginCredentials: LoginCredentialsModel): TokenModel
}