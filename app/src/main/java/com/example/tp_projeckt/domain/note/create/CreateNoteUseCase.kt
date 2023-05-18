package com.example.tp_projeckt.domain.note.create

import com.example.tp_projeckt.domain.Note

class CreateNoteUseCase(private val createNoteRepository: CreateNoteRepository) {

	suspend operator fun invoke(note: Note) {
		createNoteRepository.create(note)
	}
}