package com.example.tp_projeckt.data.note

import com.example.tp_projeckt.domain.Note

class NoteConverter {

    fun convert(from: Note): NoteModel =
        NoteModel(
            id = from.id,
            title = from.title,
            text = from.text
        )

}