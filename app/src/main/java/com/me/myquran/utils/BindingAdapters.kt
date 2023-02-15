package com.me.myquran.utils

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapters {
    @BindingAdapter("app:isVisible")
    @JvmStatic
    fun bindVisible(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    @BindingAdapter("app:adapterData")
    @JvmStatic
    fun <T> setAdapterData(recyclerView: RecyclerView, data: List<T>?) {
        data?.let {
            val adapter = recyclerView.adapter
            if (adapter is ListAdapter<*, *>) {
                @Suppress("unchecked_cast")
                (adapter as ListAdapter<T, *>).submitList(data)
            } else {
                throw IllegalArgumentException("Adapter must be a ListAdapter")
            }
        }
    }
}
