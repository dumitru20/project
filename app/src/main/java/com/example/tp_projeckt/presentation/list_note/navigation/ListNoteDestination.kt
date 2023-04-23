package com.example.tp_projeckt.presentation.list_note.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.tp_projeckt.presentation.list_note.ListNoteFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object ListNoteDestination: FragmentScreen {

	override fun createFragment(factory: FragmentFactory): Fragment =
		ListNoteFragment.newInstance()
}