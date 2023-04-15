package com.example.tp_projeckt.data.authorization.login

import com.example.tp_projeckt.domain.ErrorType
import com.example.tp_projeckt.data.token.TokenDataSource
import com.example.tp_projeckt.data.token.TokenModelConverter
import com.example.tp_projeckt.domain.login.authorization.LoginCredentials
import com.example.tp_projeckt.domain.login.authorization.LoginRepository

class LoginRepositoryImpl(
    private val loginDataSource: LoginDataSource,
    private val tokenDataSource: TokenDataSource
) : LoginRepository {

    private val loginConverter = LoginCredentialsConverter()

    private val tokenModelConverter = TokenModelConverter()

    override suspend fun authentication(loginCredentials: LoginCredentials): ErrorType {

        val loginCredentialsModel = loginConverter.convert(loginCredentials)
        val resultToken = loginDataSource.authentication(loginCredentialsModel)

        if (resultToken.errorType.isNullOrEmpty()) {
            tokenDataSource.edit(resultToken)
        }

        return tokenModelConverter.convert(resultToken)
    }
}