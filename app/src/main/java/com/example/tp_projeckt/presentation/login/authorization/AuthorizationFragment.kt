package com.example.tp_projeckt.presentation.login.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tp_projeckt.R
import com.example.tp_projeckt.databinding.FragmentAuthorizationBinding
import com.example.tp_projeckt.presentation.login.ValidationResult
import com.example.tp_projeckt.presentation.ui.ViewBindingHolder
import com.example.tp_projeckt.presentation.ui.ViewBindingHolderImpl
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthorizationFragment : Fragment(), ViewBindingHolder<FragmentAuthorizationBinding> by ViewBindingHolderImpl() {

	companion object {

		fun newInstance() = AuthorizationFragment()
	}

	private val viewModel by viewModel<AuthorizationViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View = initBinding(FragmentAuthorizationBinding.inflate(inflater))

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		withBinding {
			btnLogin.setOnClickListener {
				viewModel.login(
					textInputEditEmail.text.toString(),
					textInputEditPassword.text.toString()
				)
			}

			btnRegistration.setOnClickListener {
				viewModel.openRegistration()
			}

			toolbar.setNavigationOnClickListener {
				viewModel.back()
			}

			viewModel.state.observe(viewLifecycleOwner) { state ->
				emailContainer.setError(state.emailResult)
				passwordContainer.setError(state.passwordResult)
				btnLogin.isEnabled = !state.authorizing
			}
		}
	}

	private fun TextInputLayout.setError(errorId: ValidationResult) {
		when (errorId) {
			ValidationResult.EMPTY_FIELD_ERROR        -> {
				isErrorEnabled = true
				error = resources.getString(R.string.error_empty_field)
			}

			ValidationResult.NO_AT_SIGN_ERROR,
			ValidationResult.INCORRECT_PASSWORD_ERROR -> {
				isErrorEnabled = true
				error = resources.getString(R.string.error_validation)
			}

			ValidationResult.LOGIN_EXIST              -> {
				isErrorEnabled = true
				error = resources.getString(R.string.login_exist)

			}

			ValidationResult.VALID                    -> {
				isErrorEnabled = false
			}

			else                                      -> {
				isErrorEnabled = true
				error = resources.getString(R.string.unknown_exception)
			}
		}
	}
}