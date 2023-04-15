package com.example.tp_projeckt.domain.login.registration

import com.example.tp_projeckt.domain.ErrorType

interface RegistrationRepository {

	suspend fun register(registrationCredentials: RegistrationCredentials): ErrorType
}