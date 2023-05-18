package com.example.tp_projeckt.data.login.registration

import com.example.tp_projeckt.domain.ErrorType
import com.example.tp_projeckt.domain.login.registration.RegistrationCredentials
import com.example.tp_projeckt.domain.login.registration.RegistrationRepository

class RegistrationRepositoryImpl(
	private val registrationConverter: RegistrationCredentialsConverter,
	private val registrationDataSource: RegistrationDataSource
) : RegistrationRepository {

	override suspend fun register(registrationCredentials: RegistrationCredentials): ErrorType =
		registrationDataSource.register(registrationConverter.convert(registrationCredentials))
}