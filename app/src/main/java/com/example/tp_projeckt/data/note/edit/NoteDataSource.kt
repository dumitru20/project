package com.example.tp_projeckt.data.note.edit

import com.example.tp_projeckt.data.note.NoteModel

interface NoteDataSource {

	suspend fun get(id: Int): NoteModel

	suspend fun delete(id: Int)

	suspend fun edit(note: NoteModel)
}