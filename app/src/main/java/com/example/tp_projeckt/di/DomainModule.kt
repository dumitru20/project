package com.example.tp_projeckt.di

import com.example.tp_projeckt.domain.note.list.GetNotesUseCase
import com.example.tp_projeckt.domain.login.authorization.AuthenticateUseCase
import com.example.tp_projeckt.domain.login.registration.RegistrationUseCase
import com.example.tp_projeckt.domain.note.create.CreateNoteUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<AuthenticateUseCase> {
        AuthenticateUseCase(loginRepository = get())
    }

    factory<RegistrationUseCase> {
        RegistrationUseCase(registrationRepository = get())
    }

    factory<GetNotesUseCase> {
        GetNotesUseCase(listNoteRepository = get())
    }

    factory<CreateNoteUseCase> {
        CreateNoteUseCase(createNoteRepository = get())
    }
}