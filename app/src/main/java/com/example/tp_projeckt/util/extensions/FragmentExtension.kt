package com.example.tp_projeckt.util.extensions

import android.app.AlertDialog
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment

fun Fragment.showAlertDialog(
	@StringRes titleResId: Int,
	@StringRes positiveTextResId: Int,
	@StringRes negativeTextResId: Int? = null,
	@StringRes messageResId: Int? = null,
	cancelable: Boolean = false,
	onPositiveClick: () -> Unit = {},
	onNegativeClick: () -> Unit = {},
	@StyleRes styleResId: Int
) {
	AlertDialog.Builder(requireContext(),styleResId).apply {
		setTitle(titleResId)
		if (messageResId != null) {
			setMessage(messageResId)
		}
		setCancelable(cancelable)
		setPositiveButton(positiveTextResId) { _, _ -> onPositiveClick() }
		negativeTextResId?.let { text ->
			setNegativeButton(text) { _, _ -> onNegativeClick() }
		}
	}.show()
}