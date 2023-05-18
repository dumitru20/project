package com.example.tp_projeckt.data.note.list

import com.example.tp_projeckt.data.note.NoteModel
import retrofit2.http.GET

interface ListNoteApi {

	@GET("/api/notes")
	suspend fun get(): List<NoteModel>
}