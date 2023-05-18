package com.example.tp_projeckt.domain.note.list

import com.example.tp_projeckt.domain.Note

interface ListNoteRepository {

	suspend fun get(): List<Note>
}