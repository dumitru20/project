package com.example.tp_projeckt.data.list_note

interface ListNoteDataSource {

	suspend fun get(): List<NoteModel>
}