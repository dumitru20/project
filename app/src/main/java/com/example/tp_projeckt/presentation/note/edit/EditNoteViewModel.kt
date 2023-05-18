package com.example.tp_projeckt.presentation.note.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_projeckt.domain.Note
import com.example.tp_projeckt.domain.note.edit.DeleteNoteUseCase
import com.example.tp_projeckt.domain.note.edit.EditNoteUseCase
import com.example.tp_projeckt.domain.note.edit.GetNoteUseCase
import com.example.tp_projeckt.presentation.note.edit.navigation.EditNoteRouter
import kotlinx.coroutines.launch

class EditNoteViewModel(
	private val getNoteUseCase: GetNoteUseCase,
	private val deleteNoteUseCase: DeleteNoteUseCase,
	private val editNoteUseCase: EditNoteUseCase,
	private val router: EditNoteRouter,
	private val id: Int
) : ViewModel() {

	private val _state: MutableLiveData<EditNoteState> = MutableLiveData(EditNoteState.Initial)

	val state: LiveData<EditNoteState> = _state

	fun get() {
		viewModelScope.launch {
			_state.value = EditNoteState.Loading

			val note = getNoteUseCase(id)

			_state.value = EditNoteState.Content(
				title = note.title,
				text = note.text
			)
		}
	}

	fun delete() {
		viewModelScope.launch {
			deleteNoteUseCase(id)
			back()
		}
	}

	fun edit(
		title: String,
		description: String
	) {
		viewModelScope.launch {
			editNoteUseCase(
				Note(
					id = id,
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