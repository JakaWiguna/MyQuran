package com.me.myquran.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AyatResponse(

	@field:SerializedName("teksArab")
	@field:Expose
	val teksArab: String? = null,

	@field:SerializedName("teksLatin")
	@field:Expose
	val teksLatin: String? = null,

	@field:SerializedName("nomorAyat")
	@field:Expose
	val nomorAyat: Int? = null,

	@field:SerializedName("audio")
	@field:Expose
	val audio: AudioResponse? = null,

	@field:SerializedName("teksIndonesia")
	@field:Expose
	val teksIndonesia: String? = null
)