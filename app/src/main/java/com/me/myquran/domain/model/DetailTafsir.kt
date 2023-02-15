package com.me.myquran.domain.model

data class DetailTafsir(
    val jumlahAyat: Int? = null,
    val nama: String? = null,
    val audio: Audio? = null,
    val suratSebelumnya: Any? = null,
    val tempatTurun: String? = null,
    val arti: String? = null,
    val deskripsi: String? = null,
    val suratSelanjutnya: Any? = null,
    val nomor: Int? = null,
    val namaLatin: String? = null,
    val tafsir: List<Tafsir?>? = null
)
