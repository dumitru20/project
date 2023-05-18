package com.example.tp_projeckt.presentation.note.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_projeckt.domain.Note
import com.example.tp_projeckt.domain.note.create.CreateNoteUseCase
import com.example.tp_projeckt.presentation.note.create.navigation.CreateNoteRouter
import kotlinx.coroutines.launch

class CreateNoteViewModel(
    private val createNoteUseCase: CreateNoteUseCase,
    private val router: CreateNoteRouter
) : ViewModel() {

    fun create(
        title: String,
        description: String
    ) {
        viewModelScope.launch {
            createNoteUseCase(
                Note(
					title = title,
					text = description
                )
            )
            back()
        }
    }

    fun back() {
        router.back()
    }
}