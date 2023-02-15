package com.me.myquran.data.mapper

import com.me.myquran.data.remote.dto.DetailTafsirResponse
import com.me.myquran.data.remote.dto.SuratNextPrevResponse
import com.me.myquran.domain.model.DetailTafsir

fun DetailTafsirResponse.toDetailTafsir(): DetailTafsir {
    return DetailTafsir(
        nomor = nomor,
        nama = nama,
        namaLatin = namaLatin,
        jumlahAyat = jumlahAyat,
        tempatTurun = tempatTurun,
        arti = arti,
        deskripsi = deskripsi,
        audio = audioFull?.toAudio(),
        tafsir = tafsir?.map { it?.toTafsir() },
        suratSelanjutnya = if (suratSelanjutnya is SuratNextPrevResponse)
            suratSelanjutnya.toSuratNextPrev() else suratSelanjutnya,
        suratSebelumnya = if (suratSebelumnya is SuratNextPrevResponse)
            suratSebelumnya.toSuratNextPrev() else suratSebelumnya
    )
}