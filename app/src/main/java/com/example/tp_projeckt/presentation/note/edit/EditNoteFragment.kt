package com.example.tp_projeckt.presentation.note.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.tp_projeckt.R
import com.example.tp_projeckt.databinding.FragmentEditNoteBinding
import com.example.tp_projeckt.presentation.common.ui.ViewBindingHolder
import com.example.tp_projeckt.presentation.common.ui.ViewBindingHolderImpl
import com.example.tp_projeckt.util.extensions.showAlertDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EditNoteFragment : Fragment(), ViewBindingHolder<FragmentEditNoteBinding> by ViewBindingHolderImpl() {

    companion object {

        private const val ID = "id"

        fun newInstance(id: Int) = EditNoteFragment().apply {
            arguments = Bundle().apply {
                putInt(ID, id)
            }
        }
    }

    private val viewModel by viewModel<EditNoteViewModel> { parametersOf(arguments?.get(ID)) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = initBinding(FragmentEditNoteBinding.inflate(inflater))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.get()

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is EditNoteState.Initial -> Unit
                is EditNoteState.Loading -> handleLoadingState()
                is EditNoteState.Content -> contentStateHandling(state.title, state.text)
            }
        }

        initToolbar()
    }

    private fun initToolbar() {
        withBinding {
            toolbar.setNavigationOnClickListener {
                viewModel.back()
            }

            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.actionSave   -> {
                        showSaveConfirmationDialog()
                        true
                    }

                    R.id.actionDelete -> {
                        showDeleteConfirmationDialog()
                        true
                    }

                    else              -> {
                        false
                    }
                }
            }
        }
    }

    private fun handleLoadingState() {
        withBinding {
            progress.isVisible = true
            editTitle.isVisible = false
            editDescription.isVisible = false
        }
    }

    private fun contentStateHandling(title: String, text: String?) {
        withBinding {
            progress.isVisible = false

            editTitle.setText(title)
            editDescription.setText(text)

            editTitle.isVisible = true
            editDescription.isVisible = true
        }
    }

    private fun showSaveConfirmationDialog() {
        showAlertDialog(
            titleResId = R.string.note_save_dialog_title,
            positiveTextResId = R.string.save,
            negativeTextResId = R.string.cancel,
            messageResId = R.string.empty,
            onPositiveClick = {
                withBinding {
                    viewModel.edit(
                        title = editTitle.text.toString(),
                        description = editDescription.text.toString()
                    )
                }
            },
            styleResId = R.style.AlertDialog_Save
        )
    }

    private fun showDeleteConfirmationDialog() {
        showAlertDialog(
            titleResId = R.string.note_delete_dialog_title,
            positiveTextResId = R.string.delete,
            negativeTextResId = R.string.cancel,
            messageResId = R.string.empty,
            onPositiveClick = {
                withBinding {
                    viewModel.delete()
                }
            },
            styleResId = R.style.AlertDialog_Delete
        )
    }
}