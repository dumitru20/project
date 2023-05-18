package com.example.tp_projeckt.presentation.note.list.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.tp_projeckt.presentation.note.list.ListNoteFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object ListNoteDestination: FragmentScreen {

	override fun createFragment(factory: FragmentFactory): Fragment =
		ListNoteFragment.newInstance()
}