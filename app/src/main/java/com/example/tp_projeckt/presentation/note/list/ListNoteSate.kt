package com.example.tp_projeckt.presentation.note.list

import com.example.tp_projeckt.domain.Note

sealed interface ListNoteSate {

	object Initial : ListNoteSate

	object Loading : ListNoteSate

	object Empty : ListNoteSate

	data class Content(
		val notes: List<Note> = listOf()
	) : ListNoteSate
}