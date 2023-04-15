package com.example.tp_projeckt.presentation.login.registration.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.tp_projeckt.presentation.login.registration.RegistrationFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object RegistrationDestination : FragmentScreen {

	override fun createFragment(factory: FragmentFactory): Fragment =
		RegistrationFragment.newInstance()
}