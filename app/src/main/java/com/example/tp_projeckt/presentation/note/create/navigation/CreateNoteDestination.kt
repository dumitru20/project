package com.example.tp_projeckt.presentation.note.create.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.tp_projeckt.presentation.note.create.CreateNoteFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object CreateNoteDestination: FragmentScreen {

	override fun createFragment(factory: FragmentFactory): Fragment  =
		CreateNoteFragment.newInstance()
}