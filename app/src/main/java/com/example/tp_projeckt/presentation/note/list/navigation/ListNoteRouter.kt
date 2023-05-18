package com.example.tp_projeckt.presentation.note.list.navigation

import com.example.tp_projeckt.presentation.note.create.navigation.CreateNoteDestination
import com.example.tp_projeckt.presentation.note.edit.navigation.EditNoteDestination
import com.github.terrakok.cicerone.Router

class ListNoteRouter(private val router: Router) {

	fun openEditNote(id: Int) {
		router.navigateTo(EditNoteDestination(id))
	}

	fun openNewNote() {
		router.navigateTo(CreateNoteDestination)
	}
}