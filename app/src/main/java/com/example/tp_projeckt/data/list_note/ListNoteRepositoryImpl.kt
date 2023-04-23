package com.example.tp_projeckt.data.list_note

import com.example.tp_projeckt.domain.Note
import com.example.tp_projeckt.domain.list_note.ListNoteRepository

class ListNoteRepositoryImpl(
	private val listNoteDataSource: ListNoteDataSource,
	private val listNoteConverter: ListNoteConverter
): ListNoteRepository {

	override suspend fun get(): List<Note> =
		listNoteDataSource.get().map { item ->
			listNoteConverter.convert(item)
		}
}