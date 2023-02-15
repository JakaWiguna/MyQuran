package com.me.myquran.persentation.detail_tafsir

import android.graphics.text.LineBreaker
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.me.myquran.databinding.IDetailTafsirBinding
import com.me.myquran.domain.model.Tafsir

class TafsirAdapter(private val listener: TafsirListener) :
    ListAdapter<Tafsir, TafsirHolder>(TafsirModelDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TafsirHolder {
        val binding = IDetailTafsirBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TafsirHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: TafsirHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class TafsirModelDiffCallback : DiffUtil.ItemCallback<Tafsir>() {
    override fun areItemsTheSame(oldItem: Tafsir, newItem: Tafsir): Boolean {
        return oldItem.ayat == newItem.ayat
    }

    override fun areContentsTheSame(oldItem: Tafsir, newItem: Tafsir): Boolean {
        return oldItem == newItem
    }
}

class TafsirHolder(private val binding: IDetailTafsirBinding, private val listener: TafsirListener) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Tafsir) {
        binding.item = item
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.tvTeks.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
        } else {
            binding.tvTeks.gravity = Gravity.FILL
        }
        binding.executePendingBindings()

        binding.ivShare.setOnClickListener {
            listener.onShareClick(item)
        }
        binding.ivBookmark.setOnClickListener {
            listener.onBookmarkClick(item)
        }
    }
}

interface TafsirListener {
    fun onShareClick(tafsir: Tafsir)
    fun onBookmarkClick(tafsir: Tafsir)
}