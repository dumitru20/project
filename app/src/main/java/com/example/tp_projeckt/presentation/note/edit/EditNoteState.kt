package com.example.tp_projeckt.presentation.note.edit

sealed interface EditNoteState {

	object Initial : EditNoteState

	object Loading : EditNoteState

	data class Content(
		val title: String,
		val text: String?
	) : EditNoteState
}