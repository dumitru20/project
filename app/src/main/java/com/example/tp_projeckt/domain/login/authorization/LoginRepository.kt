package com.example.tp_projeckt.domain.login.authorization

import com.example.tp_projeckt.domain.ErrorType

interface LoginRepository {

	suspend fun authentication(loginCredentials: LoginCredentials) : ErrorType
}