package com.me.myquran.data.mapper

import com.me.myquran.data.remote.dto.SuratNextPrevResponse
import com.me.myquran.domain.model.SuratNextPrev

fun SuratNextPrevResponse.toSuratNextPrev(): SuratNextPrev {
    return SuratNextPrev(
        nomor = nomor,
        nama = nama,
        namaLatin = namaLatin,
        jumlahAyat = jumlahAyat,
    )
}