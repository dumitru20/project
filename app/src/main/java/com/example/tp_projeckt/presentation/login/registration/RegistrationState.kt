package com.example.tp_projeckt.presentation.login.registration

import com.example.tp_projeckt.presentation.login.ValidationResult

data class RegistrationState(
	val emailResult: ValidationResult,
	val passwordResult: ValidationResult,
	val registration: Boolean = false
)
