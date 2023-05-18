package com.example.tp_projeckt.data.note.edit

import com.example.tp_projeckt.data.note.NoteModel

class NoteDataSourceImpl(private val noteApi: NoteApi): NoteDataSource {

	override suspend fun get(id: Int): NoteModel =
		noteApi.get(id)

	override suspend fun delete(id: Int) {
		noteApi.delete(id)
	}

	override suspend fun edit(note: NoteModel) {
		noteApi.edit(note)
	}
}