package com.example.tp_projeckt.presentation.login

enum class ValidationResult {
	EMPTY_FIELD_ERROR,
	NO_AT_SIGN_ERROR,
	INCORRECT_PASSWORD_ERROR,
	DIFFERENT_PASSWORDS,
	LOGIN_EXIST,
	VALID
}