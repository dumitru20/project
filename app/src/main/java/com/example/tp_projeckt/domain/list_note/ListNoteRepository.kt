package com.example.tp_projeckt.domain.list_note

import com.example.tp_projeckt.domain.Note

interface ListNoteRepository {

	suspend fun get(): List<Note>
}