package com.example.tp_projeckt.presentation.note.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_projeckt.R
import com.example.tp_projeckt.databinding.NoteListItemBinding
import com.example.tp_projeckt.domain.Note

class ListNoteAdapter(
	private val onItemClick: (note: Note) -> Unit
) : ListAdapter<Note, ListNoteAdapter.ViewNoteHolder>(DiffCallback){

	private companion object {

		val DiffCallback = object : DiffUtil.ItemCallback<Note>() {
			override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean =
				oldItem.id == newItem.id

			override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean =
				oldItem == newItem
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewNoteHolder =
		ViewNoteHolder(
			LayoutInflater
				.from(parent.context)
				.inflate(R.layout.note_list_item, parent, false),
			onItemClick,
		)

	override fun onBindViewHolder(holder: ViewNoteHolder, position: Int) {
		holder.bind(getItem(position))
	}

	class ViewNoteHolder(
		view: View,
		onItemClick: (note: Note) -> Unit,
	) : RecyclerView.ViewHolder(view) {

		private val binding = NoteListItemBinding.bind(view)
		private lateinit var note : Note

		init {
			binding.root.setOnClickListener { onItemClick(note) }
		}

		fun bind(note: Note) {
			this.note = note
			binding.title.text = note.title
		}
	}
}