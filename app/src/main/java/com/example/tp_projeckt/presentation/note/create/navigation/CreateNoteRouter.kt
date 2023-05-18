package com.example.tp_projeckt.presentation.note.create.navigation

import com.example.tp_projeckt.presentation.note.list.navigation.ListNoteDestination
import com.github.terrakok.cicerone.Router

class CreateNoteRouter(private val router: Router) {

	fun back() {
		router.backTo(ListNoteDestination)
	}
}