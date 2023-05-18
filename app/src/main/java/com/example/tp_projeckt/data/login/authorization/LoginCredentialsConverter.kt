package com.example.tp_projeckt.data.login.authorization

import com.example.tp_projeckt.domain.login.authorization.LoginCredentials

class LoginCredentialsConverter {

	fun convert(from: LoginCredentials): LoginCredentialsModel = LoginCredentialsModel(from.login, from.password)

}