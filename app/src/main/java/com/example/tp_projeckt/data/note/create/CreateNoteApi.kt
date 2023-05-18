package com.example.tp_projeckt.data.note.create

import com.example.tp_projeckt.data.note.NoteModel
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateNoteApi {

	@POST("/api/note")
	suspend fun create(@Body note: NoteModel)
}