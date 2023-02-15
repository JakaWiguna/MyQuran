package com.me.myquran.persentation.daftar_surat

import com.me.myquran.domain.model.DaftarSurat

data class DaftarSuratState(
    var isLoading: Boolean = true,
    var daftarSurahList: List<DaftarSurat> = emptyList(),
)
