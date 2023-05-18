package com.example.tp_projeckt.domain.note.edit

import com.example.tp_projeckt.domain.Note

interface NoteRepository {

	suspend fun get(id: Int): Note

	suspend fun delete(id: Int)

	suspend fun edit(note: Note)
}