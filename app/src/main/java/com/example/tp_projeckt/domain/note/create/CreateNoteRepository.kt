package com.example.tp_projeckt.domain.note.create

import com.example.tp_projeckt.domain.Note

interface CreateNoteRepository {

	suspend fun create(note: Note)
}