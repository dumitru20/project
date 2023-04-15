package com.example.tp_projeckt.presentation.login

sealed class ValidationRule(protected val input: String) {

	class IsPassword(input: String) : ValidationRule(input) {

		private companion object {

			const val MIN_LENGTH = 8
		}

		override fun apply(): ValidationResult =
			if (input.length >= MIN_LENGTH
				&& input.any { it.isUpperCase() }
				&& input.any { it.isLowerCase() }
				&& input.any { it.isDigit() }
				&& input.any { !it.isLetterOrDigit() && !it.isWhitespace() }
			) {
				ValidationResult.VALID
			} else {
				ValidationResult.INCORRECT_PASSWORD_ERROR
			}
	}

	class IsEmail(input: String) : ValidationRule(input) {
		private companion object {

			const val AT_SIGN = '@'
			const val AT_COM = ".com"
			const val AT_RU = ".ru"
		}

		override fun apply(): ValidationResult =
			if (input.contains(AT_SIGN)
				&& input.contains(AT_COM)
				|| input.contains(AT_RU)
			) {
				ValidationResult.VALID
			} else {
				ValidationResult.NO_AT_SIGN_ERROR
			}
	}

	class IsNotBlank(input: String) : ValidationRule(input) {

		override fun apply(): ValidationResult =
			when {
				input.isBlank() -> ValidationResult.EMPTY_FIELD_ERROR
				else            -> ValidationResult.VALID
			}
	}

	class IsDifferentPasswords(input: String, private val password: String) : ValidationRule(input) {

		override fun apply(): ValidationResult =
			if (input == password) {
				ValidationResult.VALID
			} else {
				ValidationResult.DIFFERENT_PASSWORDS
			}
	}

	abstract fun apply(): ValidationResult
}
