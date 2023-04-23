package com.example.tp_projeckt.presentation.common

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class ItemMarginDecoration(
	@DimenRes private val margin: Int
) : RecyclerView.ItemDecoration() {

	override fun getItemOffsets(
		outRect: Rect,
		view: View,
		parent: RecyclerView,
		state: RecyclerView.State
	) {
		super.getItemOffsets(outRect, view, parent, state)

		val position = parent.getChildLayoutPosition(view)
		if (position == RecyclerView.NO_POSITION || position == 0) return

		val margin = parent.context.resources.getDimensionPixelSize(margin)

		outRect.apply {
			top = margin
			if (position == state.itemCount - 1) {
				bottom = margin
			}
		}
	}
}