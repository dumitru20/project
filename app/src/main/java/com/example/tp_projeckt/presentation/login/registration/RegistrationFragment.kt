package com.example.tp_projeckt.presentation.login.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tp_projeckt.R
import com.example.tp_projeckt.databinding.FragmentRegistrationBinding
import com.example.tp_projeckt.presentation.login.ValidationResult
import com.example.tp_projeckt.presentation.common.ui.ViewBindingHolder
import com.example.tp_projeckt.presentation.common.ui.ViewBindingHolderImpl
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment(), ViewBindingHolder<FragmentRegistrationBinding> by ViewBindingHolderImpl() {

	companion object {

		fun newInstance() = RegistrationFragment()
	}

	private val viewModel by viewModel<RegistrationViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View = initBinding(FragmentRegistrationBinding.inflate(inflater))

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		withBinding {

			viewModel.state.observe(viewLifecycleOwner) { state ->
				emailContainer.setError(state.emailResult)
				passwordContainer.setError(state.passwordResult)
				btnAuthorization.isEnabled = !state.registration
			}

			btnRegistration.setOnClickListener {
				viewModel.registration(
					textInputEditEmail.text.toString(),
					textInputEditPassword.text.toString(),
				)
			}

			toolbar.setNavigationOnClickListener {
				viewModel.back()
			}

			btnAuthorization.setOnClickListener {
				viewModel.openAuthorization()
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

			ValidationResult.VALID                    -> {
				isErrorEnabled = false
			}

			ValidationResult.LOGIN_EXIST              -> {
				isErrorEnabled = true
				error = resources.getString(R.string.login_exist_registration)
			}
		}
	}
}