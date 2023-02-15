package com.me.myquran.domain.model

data class DetailSurat(
    val jumlahAyat: Int? = null,
    val nama: String? = null,
    val audio: Audio? = null,
    val suratSebelumnya: Any? = null,
    val tempatTurun: String? = null,
    val ayat: List<Ayat?>? = null,
    val arti: String? = null,
    val deskripsi: String? = null,
    val suratSelanjutnya: Any? = null,
    val nomor: Int? = null,
    val namaLatin: String? = null
)
