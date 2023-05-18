package com.example.tp_projeckt.data.note.list

import com.example.tp_projeckt.data.note.NoteModel

interface ListNoteDataSource {

	suspend fun get(): List<NoteModel>
}