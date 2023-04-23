package com.example.tp_projeckt.data.list_note

class ListNoteDataSourceImpl(private val service: ListNoteApi) : ListNoteDataSource {

	override suspend fun get(): List<NoteModel> =
		service.get()
}