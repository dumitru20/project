package com.example.tp_projeckt.presentation.note.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.tp_projeckt.R
import com.example.tp_projeckt.databinding.FragmentListNoteBinding
import com.example.tp_projeckt.domain.Note
import com.example.tp_projeckt.presentation.common.ItemMarginDecoration
import com.example.tp_projeckt.presentation.common.ui.ViewBindingHolder
import com.example.tp_projeckt.presentation.common.ui.ViewBindingHolderImpl
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListNoteFragment : Fragment(), ViewBindingHolder<FragmentListNoteBinding> by ViewBindingHolderImpl() {

	companion object {

		fun newInstance() = ListNoteFragment()
	}

	private val viewModel by viewModel<ListNoteViewModel>()
	private var listNoteAdapter: ListNoteAdapter? = null

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View = initBinding(FragmentListNoteBinding.inflate(inflater))

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel.getNotes()

		viewModel.state.observe(viewLifecycleOwner) { state ->
			when (state) {
				is ListNoteSate.Initial -> Unit
				is ListNoteSate.Loading -> handleLoadingState()
				is ListNoteSate.Empty -> emptyStateHandling()
				is ListNoteSate.Content -> contentStateHandling(state.notes)
			}
		}

		binding.fab.setOnClickListener {
			viewModel.openNewNote()
		}

		initRecyclerView()
	}

	private fun handleLoadingState() {
		withBinding {

			progress.isVisible = true
		}
	}

	private fun emptyStateHandling() {
		withBinding {

			progress.isVisible = false
		}
	}

	private fun contentStateHandling(documents: List<Note>) {
		binding.progress.isVisible = false
		listNoteAdapter?.submitList(documents)
	}

	private fun initRecyclerView() {
		listNoteAdapter = ListNoteAdapter { viewModel.openEditNote(it.id) }

		binding.documentRecyclerView.run {
			adapter = listNoteAdapter
			addItemDecoration(ItemMarginDecoration(R.dimen.spacing_8dp))
		}
	}

	override fun onDestroyView() {
		listNoteAdapter = null
		super.onDestroyView()
	}
}