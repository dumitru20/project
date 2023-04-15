package com.example.tp_projeckt.presentation.title.navigation

import com.example.tp_projeckt.presentation.login.authorization.navigation.AuthorizationDestination
import com.github.terrakok.cicerone.Router

class TitleRouter(private val router: Router) {

	fun openAuthorization() {
		router.navigateTo(AuthorizationDestination)
	}
//
//	fun openRegistration() {
//		router.navigateTo(RegistrationDestination)
//	}
}