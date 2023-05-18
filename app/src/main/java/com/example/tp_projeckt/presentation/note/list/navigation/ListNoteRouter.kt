package com.example.tp_projeckt.presentation.note.list.navigation

import com.example.tp_projeckt.presentation.note.create.navigation.CreateNoteDestination
import com.github.terrakok.cicerone.Router

class ListNoteRouter(private val router: Router) {

	fun openEditNote(id: Int, title: String) {
		//TODO добавить переход на заметку
	}

	fun openNewNote() {
		router.navigateTo(CreateNoteDestination)
	}
}