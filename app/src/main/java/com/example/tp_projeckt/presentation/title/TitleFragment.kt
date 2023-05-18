package com.example.tp_projeckt.presentation.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tp_projeckt.presentation.common.ui.ViewBindingHolder
import com.example.tp_projeckt.presentation.common.ui.ViewBindingHolderImpl
import com.example.tp_projeckt.databinding.FragmentTitleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TitleFragment : Fragment(), ViewBindingHolder<FragmentTitleBinding> by ViewBindingHolderImpl() {

	companion object {

		fun newInstance() = TitleFragment()
	}

	private val viewModel by viewModel<TitleVIewModel>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View = initBinding(FragmentTitleBinding.inflate(inflater))

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		withBinding {
			btnAuthorization.setOnClickListener {
				viewModel.openAuthorization()
			}

			btnRegistration.setOnClickListener {
				viewModel.openRegistration()
			}
		}
	}
}