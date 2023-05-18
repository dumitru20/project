package com.example.tp_projeckt.presentation.note.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_projeckt.domain.note.list.GetNotesUseCase
import com.example.tp_projeckt.presentation.note.list.navigation.ListNoteRouter
import kotlinx.coroutines.launch

class ListNoteViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val router: ListNoteRouter
) : ViewModel() {

	private val _state: MutableLiveData<ListNoteSate> = MutableLiveData(ListNoteSate.Initial)

	val state: LiveData<ListNoteSate> = _state

	fun getNotes() {
		viewModelScope.launch {
			_state.value = ListNoteSate.Loading

			val noteList = getNotesUseCase()

			_state.value = if (noteList.isNotEmpty()) {
				val reversedNoteList = noteList.reversed()
				ListNoteSate.Content(reversedNoteList)
			} else {
				ListNoteSate.Empty
			}
		}
	}

	fun openNewNote() {
		router.openNewNote()
	}

	fun openEditNote(id: Int) {
		router.openEditNote(id)
	}
}