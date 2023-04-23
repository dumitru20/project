package com.example.tp_projeckt.domain.list_note

import com.example.tp_projeckt.domain.Note

class GetNotesUseCase(private val listNoteRepository: ListNoteRepository) {

	suspend operator fun invoke(): List<Note> =
		listNoteRepository.get()
}