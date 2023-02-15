package com.me.myquran.persentation.daftar_surat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.me.myquran.databinding.IDaftarSuratBinding
import com.me.myquran.domain.model.DaftarSurat

class DaftarSuratAdapter(private val listener: DaftarSuratListener) : ListAdapter<DaftarSurat, DaftarSuratHolder>(MyDataModelDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaftarSuratHolder {
        val binding = IDaftarSuratBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DaftarSuratHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: DaftarSuratHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class MyDataModelDiffCallback : DiffUtil.ItemCallback<DaftarSurat>() {
    override fun areItemsTheSame(oldItem: DaftarSurat, newItem: DaftarSurat): Boolean {
        return oldItem.nomor == newItem.nomor
    }

    override fun areContentsTheSame(oldItem: DaftarSurat, newItem: DaftarSurat): Boolean {
        return oldItem == newItem
    }
}

class DaftarSuratHolder(private val binding: IDaftarSuratBinding, private val listener: DaftarSuratListener) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DaftarSurat) {
        binding.item = item
//        binding.badgeSize = binding.root.context.dpToPx(32f)
        binding.executePendingBindings()
        binding.btnTafsir.setOnClickListener {
            listener.onDaftarTafsirClick(item)
        }
        binding.btnSurat.setOnClickListener {
            listener.onDaftarSuratClick(item)
        }
    }
}

interface DaftarSuratListener {
    fun onDaftarSuratClick(daftarSurat: DaftarSurat)
    fun onDaftarTafsirClick(daftarSurat: DaftarSurat)
}