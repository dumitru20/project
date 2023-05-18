package com.example.tp_projeckt.data.note

import com.example.tp_projeckt.domain.Note

class GetNotesConverter {

	fun convert(from: NoteModel): Note =
		Note(
			id = from.id,
			title = from.title,
			text = from.text
		)
}