package com.example.tp_projeckt.presentation.list_note

import com.example.tp_projeckt.domain.Note

sealed interface ListNoteSate {

	object Initial : ListNoteSate

	object Loading : ListNoteSate

	object Empty : ListNoteSate

	data class Content(
		val notes: List<Note> = listOf()
	) : ListNoteSate
}