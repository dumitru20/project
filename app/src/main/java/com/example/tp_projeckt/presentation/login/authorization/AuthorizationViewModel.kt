package com.example.tp_projeckt.presentation.login.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_projeckt.domain.login.authorization.AuthenticateUseCase
import com.example.tp_projeckt.presentation.login.authorization.navigation.AuthorizationRouter
import com.example.tp_projeckt.domain.login.authorization.LoginCredentials
import com.example.tp_projeckt.presentation.login.ValidationResult
import com.example.tp_projeckt.presentation.login.ValidationRule
import kotlinx.coroutines.launch

class AuthorizationViewModel(
	private val authenticateUseCase: AuthenticateUseCase,
	private val router: AuthorizationRouter
): ViewModel() {

	private val _state: MutableLiveData<LoginState> = MutableLiveData(
		LoginState(
			emailResult = ValidationResult.VALID,
			passwordResult = ValidationResult.VALID
		)
	)
	val state: LiveData<LoginState> = _state

	fun login(
		email: String,
		password: String
	) {
		val currentState = requireNotNull(_state.value) { "State cannot be null" }
		_state.value = currentState.copy(
			authorizing = true
		)

		viewModelScope.launch {
			val notBlankValidationResult = listOf(
				ValidationRule.IsNotBlank(email),
				ValidationRule.IsNotBlank(password)
			)
				.map { it.apply() }.firstOrNull {
					it != ValidationResult.VALID
				} ?: ValidationResult.VALID

			val incorrectDataValidationResult = listOf(
				ValidationRule.IsEmail(email),
				ValidationRule.IsPassword(password)
			)
				.map { it.apply() }.firstOrNull {
					it != ValidationResult.VALID
				} ?: ValidationResult.VALID

			when {
				notBlankValidationResult != ValidationResult.VALID      -> {
					_state.value = LoginState(
						emailResult = ValidationResult.EMPTY_FIELD_ERROR,
						passwordResult = ValidationResult.EMPTY_FIELD_ERROR
					)
				}

				incorrectDataValidationResult != ValidationResult.VALID -> {
					_state.value = LoginState(
						emailResult = ValidationResult.NO_AT_SIGN_ERROR,
						passwordResult = ValidationResult.INCORRECT_PASSWORD_ERROR
					)
				}

				else                                                    -> {
					authorization(
						LoginCredentials(
							login = email,
							password = password
						)
					)
				}
			}
		}
	}

	private suspend fun authorization(auth: LoginCredentials) {
		val currentState = requireNotNull(_state.value) { "State cannot be null" }

		_state.value = currentState.copy(
			emailResult = ValidationResult.VALID,
			passwordResult = ValidationResult.VALID
		)

		val error = authenticateUseCase(auth)

		if (error.errorType.isNullOrEmpty()) {
			router.openListNote()
		} else {
			_state.value = currentState.copy(
				emailResult = ValidationResult.LOGIN_EXIST,
				passwordResult = ValidationResult.VALID,
				authorizing = false
			)
		}
	}

	fun openRegistration() {
		router.openRegistration()
	}

	fun back() {
		router.back()
	}
}