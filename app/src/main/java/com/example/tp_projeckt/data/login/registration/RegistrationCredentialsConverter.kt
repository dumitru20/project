package com.example.tp_projeckt.data.login.registration

import com.example.tp_projeckt.domain.login.registration.RegistrationCredentials

class RegistrationCredentialsConverter {

	fun convert(from: RegistrationCredentials): RegistrationCredentialsModel = RegistrationCredentialsModel(from.login, from.password)

}