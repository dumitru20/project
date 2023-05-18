package com.example.tp_projeckt.presentation.common.ui

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

interface ViewBindingHolder<T : ViewBinding> {

	val binding: T

	fun withBinding(block: (T.() -> Unit)? = null)
	fun Fragment.initBinding(binding: T): View
}

class ViewBindingHolderImpl<T : ViewBinding> : ViewBindingHolder<T> {

	private var _binding: T? = null
	override val binding: T
		get() = requireNotNull(_binding) { "Binding is not initialized" }

	private var lifecycle: Lifecycle? = null
	private val lifecycleObserver = ViewBindingLifecycleObserver()

	override fun withBinding(block: (T.() -> Unit)?) {
		with(binding) { block?.invoke(this) }
	}

	override fun Fragment.initBinding(binding: T): View {
		_binding = binding

		this@ViewBindingHolderImpl.lifecycle = viewLifecycleOwner.lifecycle.apply {
			addObserver(lifecycleObserver)
		}

		return binding.root
	}

	private inner class ViewBindingLifecycleObserver : DefaultLifecycleObserver {

		override fun onDestroy(owner: LifecycleOwner) {
			lifecycle?.removeObserver(lifecycleObserver)
			lifecycle = null
			_binding = null
			super.onDestroy(owner)
		}
	}
}