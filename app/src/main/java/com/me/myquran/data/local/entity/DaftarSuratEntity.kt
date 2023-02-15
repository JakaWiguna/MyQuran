package com.me.myquran.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DaftarSuratEntity(
	@PrimaryKey val nomor: Int,
	val jumlahAyat: Int? = null,
	val nama: String? = null,
	val tempatTurun: String? = null,
	val arti: String? = null,
	val deskripsi: String? = null,
	val namaLatin: String? = null
)
