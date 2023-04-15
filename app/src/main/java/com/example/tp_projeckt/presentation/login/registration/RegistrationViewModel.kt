package com.example.tp_projeckt.presentation.login.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_projeckt.domain.login.registration.RegistrationCredentials
import com.example.tp_projeckt.domain.login.registration.RegistrationUseCase
import com.example.tp_projeckt.presentation.login.ValidationResult
import com.example.tp_projeckt.presentation.login.ValidationRule
import com.example.tp_projeckt.presentation.login.registration.navigation.RegistrationRouter
import kotlinx.coroutines.launch

class RegistrationViewModel(
	private val registrationUseCase: RegistrationUseCase,
	private val router: RegistrationRouter
) : ViewModel() {

	private val _state: MutableLiveData<RegistrationState> = MutableLiveData(
		RegistrationState(
			emailResult = ValidationResult.VALID,
			passwordResult = ValidationResult.VALID
		)
	)
	val state: LiveData<RegistrationState> = _state

	fun registration(
		email: String,
		password: String,
	) {
		val currentState = requireNotNull(_state.value) { "State cannot be null" }
		_state.value = currentState.copy(
			registration = true
		)

		viewModelScope.launch {
			val notBlankValidationResult = listOf(
				ValidationRule.IsNotBlank(email),
				ValidationRule.IsNotBlank(password),
			)
				.map { it.apply() }.firstOrNull {
					it != ValidationResult.VALID
				} ?: ValidationResult.VALID

			val incorrectEmailValidationResult = listOf(
				ValidationRule.IsEmail(email),
			)
				.map { it.apply() }.firstOrNull {
					it != ValidationResult.VALID
				} ?: ValidationResult.VALID

			val incorrectPasswordValidationResult = listOf(
				ValidationRule.IsPassword(password)
			)
				.map { it.apply() }.firstOrNull {
					it != ValidationResult.VALID
				} ?: ValidationResult.VALID

			when {
				notBlankValidationResult != ValidationResult.VALID          -> {
					_state.value = RegistrationState(
						emailResult = ValidationResult.EMPTY_FIELD_ERROR,
						passwordResult = ValidationResult.EMPTY_FIELD_ERROR
					)
				}

				incorrectEmailValidationResult != ValidationResult.VALID    -> {
					_state.value = RegistrationState(
						emailResult = ValidationResult.NO_AT_SIGN_ERROR,
						passwordResult = ValidationResult.VALID
					)
				}

				incorrectPasswordValidationResult != ValidationResult.VALID -> {
					_state.value = RegistrationState(
						emailResult = ValidationResult.VALID,
						passwordResult = ValidationResult.INCORRECT_PASSWORD_ERROR
					)
				}

				else                                                        -> {
					register(
						RegistrationCredentials(
							login = email,
							password = password
						)
					)
				}
			}
		}
	}

	private suspend fun register(registrationCredentials: RegistrationCredentials) {
		val currentState = requireNotNull(_state.value) { "State cannot be null" }

		val error = registrationUseCase(registrationCredentials)

		if (error.errorType.isNullOrEmpty()) {
			_state.value = currentState.copy(
				emailResult = ValidationResult.LOGIN_EXIST,
				passwordResult = ValidationResult.VALID,
			)
		} else {
			_state.value = currentState.copy(
				emailResult = ValidationResult.VALID,
				passwordResult = ValidationResult.VALID,
			)
			openAuthorization()
		}
	}

	fun openAuthorization() {
		router.openAuthorization()
	}

	fun back() {
		router.back()
	}
}