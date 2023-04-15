package com.example.tp_projeckt.data.authorization.registration

import com.example.tp_projeckt.domain.ErrorType

interface RegistrationDataSource {

	suspend fun register(registrationCredentialsModel: RegistrationCredentialsModel): ErrorType
}