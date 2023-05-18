package com.example.tp_projeckt.data.note.edit

import com.example.tp_projeckt.data.note.NoteModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NoteApi {

	@GET("/api/note/{id}")
	suspend fun get(@Path("id") id: Int): NoteModel

	@DELETE("/api/note/{id}")
	suspend fun delete(@Path("id") id: Int)

	@POST("/api/note")
	suspend fun edit(@Body note: NoteModel)
}