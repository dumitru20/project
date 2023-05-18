package com.example.tp_projeckt.presentation.note.edit.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class EditNoteDestination(private val id: Int): FragmentScreen {

	override fun createFragment(factory: FragmentFactory): Fragment =
		EditNoteFragment.newInstance(id)
}