package com.example.tp_projeckt.data.list_note

import retrofit2.http.GET

interface ListNoteApi {

	@GET
	suspend fun get(): List<NoteModel>
}