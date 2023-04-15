package com.example.tp_projeckt.presentation.login.authorization.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.tp_projeckt.presentation.login.authorization.AuthorizationFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen


object AuthorizationDestination : FragmentScreen {

	override fun createFragment(factory: FragmentFactory): Fragment =
		AuthorizationFragment.newInstance()
}