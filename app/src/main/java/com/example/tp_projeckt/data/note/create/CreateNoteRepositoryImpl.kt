package com.example.tp_projeckt.data.note.create

import com.example.tp_projeckt.data.note.NoteConverter
import com.example.tp_projeckt.domain.Note
import com.example.tp_projeckt.domain.note.create.CreateNoteRepository

class CreateNoteRepositoryImpl(
	private val createNoteDataSource: CreateNoteDataSource,
	private val converter: NoteConverter
) : CreateNoteRepository {

	override suspend fun create(note: Note) {
		createNoteDataSource.create(converter.convert(note))
	}
}