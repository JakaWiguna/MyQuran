package com.me.myquran.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DaftarSuratResponse(

	@field:SerializedName("jumlahAyat")
	@field:Expose
	val jumlahAyat: Int? = null,

	@field:SerializedName("nama")
	@field:Expose
	val nama: String? = null,

	@field:SerializedName("audioFull")
	@field:Expose
	val audio: AudioResponse? = null,

	@field:SerializedName("tempatTurun")
	@field:Expose
	val tempatTurun: String? = null,

	@field:SerializedName("arti")
	@field:Expose
	val arti: String? = null,

	@field:SerializedName("deskripsi")
	@field:Expose
	val deskripsi: String? = null,

	@field:SerializedName("nomor")
	@field:Expose
	val nomor: Int? = null,

	@field:SerializedName("namaLatin")
	@field:Expose
	val namaLatin: String? = null
)