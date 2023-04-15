package com.example.tp_projeckt.data.authorization.registration

import com.example.tp_projeckt.domain.ErrorType

class RegistrationDataSourceImpl(private val service: RegistrationApi) : RegistrationDataSource {

	override suspend fun register(registrationCredentialsModel: RegistrationCredentialsModel): ErrorType =
		service.register(registrationCredentialsModel)
}