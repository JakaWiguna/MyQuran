package com.me.myquran.domain.model

data class Ayat(
	val teksArab: String? = null,
	val teksLatin: String? = null,
	val nomorAyat: Int? = null,
	val audio: Audio? = null,
	val teksIndonesia: String? = null
)
