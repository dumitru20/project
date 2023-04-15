package com.example.tp_projeckt.presentation.title

import androidx.lifecycle.ViewModel
import com.example.tp_projeckt.presentation.title.navigation.TitleRouter

class TitleVIewModel(
	private val router: TitleRouter
) : ViewModel() {

	fun openAuthorization() {
		router.openAuthorization()
	}

	fun openRegistration() {
		router.openRegistration()
	}
}