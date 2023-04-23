package com.example.tp_projeckt.data.list_note

import com.example.tp_projeckt.domain.Note

class ListNoteConverter {

	fun convert(from: NoteModel): Note =
		Note(
			id = from.id,
			title = from.title,
			value = from.value
		)
}