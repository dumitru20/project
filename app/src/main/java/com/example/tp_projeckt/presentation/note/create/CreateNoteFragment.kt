package com.example.tp_projeckt.presentation.note.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tp_projeckt.R
import com.example.tp_projeckt.databinding.FragmentCreateNoteBinding
import com.example.tp_projeckt.presentation.ui.ViewBindingHolder
import com.example.tp_projeckt.presentation.ui.ViewBindingHolderImpl
import com.example.tp_projeckt.util.extensions.showAlertDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateNoteFragment : Fragment(), ViewBindingHolder<FragmentCreateNoteBinding> by ViewBindingHolderImpl() {

    companion object {

        fun newInstance() = CreateNoteFragment()
    }

    private val viewModel by viewModel<CreateNoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = initBinding(FragmentCreateNoteBinding.inflate(inflater))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
    }

    private fun initToolbar() {
        withBinding {
            toolbar.setNavigationOnClickListener {
                viewModel.back()
            }

            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.actionSave -> {
                        showSaveConfirmationDialog()
                        true
                    }

                    else            -> {
                        false
                    }
                }
            }
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
                    viewModel.create(
                        title = editTitle.text.toString(),
                        description = editDescription.text.toString()
                    )
                }
            },
            styleResId = R.style.AlertDialog_Save
        )
    }
}