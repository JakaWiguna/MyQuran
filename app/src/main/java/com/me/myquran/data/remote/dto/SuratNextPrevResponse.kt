package com.me.myquran.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SuratNextPrevResponse(

	@field:SerializedName("jumlahAyat")
	@field:Expose
	val jumlahAyat: Int? = null,

	@field:SerializedName("nama")
	@field:Expose
	val nama: String? = null,

	@field:SerializedName("nomor")
	@field:Expose
	val nomor: Int? = null,

	@field:SerializedName("namaLatin")
	@field:Expose
	val namaLatin: String? = null
)