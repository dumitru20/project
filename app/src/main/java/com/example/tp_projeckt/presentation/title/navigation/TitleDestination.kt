package com.example.tp_projeckt.presentation.title.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.tp_projeckt.presentation.title.TitleFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object TitleDestination: FragmentScreen {

	override fun createFragment(factory: FragmentFactory): Fragment =
		TitleFragment.newInstance()
}