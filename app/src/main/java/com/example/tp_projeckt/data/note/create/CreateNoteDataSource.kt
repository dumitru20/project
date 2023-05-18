package com.example.tp_projeckt.data.note.create

import com.example.tp_projeckt.data.note.NoteModel

interface CreateNoteDataSource {

	suspend fun create(note: NoteModel)
}