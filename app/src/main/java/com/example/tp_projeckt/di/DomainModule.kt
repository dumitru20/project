package com.example.tp_projeckt.di

import com.example.tp_projeckt.domain.login.authorization.AuthenticateUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<AuthenticateUseCase> {
        AuthenticateUseCase(loginRepository = get())
    }
}