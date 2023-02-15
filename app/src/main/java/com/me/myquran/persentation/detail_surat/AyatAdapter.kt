package com.me.myquran.persentation.detail_surat

import android.graphics.text.LineBreaker
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.me.myquran.databinding.IDetailSuratBinding
import com.me.myquran.domain.model.Ayat

class AyatAdapter(private val listener: AyatListener) :
    ListAdapter<Ayat, AyatHolder>(AyatModelDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatHolder {
        val binding = IDetailSuratBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AyatHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: AyatHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class AyatModelDiffCallback : DiffUtil.ItemCallback<Ayat>() {
    override fun areItemsTheSame(oldItem: Ayat, newItem: Ayat): Boolean {
        return oldItem.nomorAyat == newItem.nomorAyat
    }

    override fun areContentsTheSame(oldItem: Ayat, newItem: Ayat): Boolean {
        return oldItem == newItem
    }
}

class AyatHolder(private val binding: IDetailSuratBinding, private val listener: AyatListener) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Ayat) {
        binding.item = item
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.tvTeksLatin.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
            binding.tvTeksIndonesia.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
        } else {
            binding.tvTeksLatin.gravity = Gravity.FILL
            binding.tvTeksIndonesia.gravity = Gravity.FILL
        }
        binding.executePendingBindings()

        binding.ivShare.setOnClickListener {
            listener.onShareClick(item)
        }
        binding.ivPlay.setOnClickListener {
            listener.onPlayClick(item)
        }
        binding.ivBookmark.setOnClickListener {
            listener.onBookmarkClick(item)
        }
    }
}

interface AyatListener {
    fun onShareClick(ayat: Ayat)
    fun onPlayClick(ayat: Ayat)
    fun onBookmarkClick(ayat: Ayat)
}