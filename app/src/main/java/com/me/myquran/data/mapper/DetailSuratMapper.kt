package com.me.myquran.data.mapper

import com.me.myquran.data.remote.dto.DetailSuratResponse
import com.me.myquran.data.remote.dto.SuratNextPrevResponse
import com.me.myquran.domain.model.DetailSurat

fun DetailSuratResponse.toDetailSurat(): DetailSurat {
    return DetailSurat(
        nomor = nomor,
        nama = nama,
        namaLatin = namaLatin,
        jumlahAyat = jumlahAyat,
        tempatTurun = tempatTurun,
        arti = arti,
        deskripsi = deskripsi,
        audio = audioFull?.toAudio(),
        ayat = ayat?.map { it?.toAyat() },
        suratSelanjutnya = if (suratSelanjutnya is SuratNextPrevResponse)
            suratSelanjutnya.toSuratNextPrev() else suratSelanjutnya,
        suratSebelumnya = if (suratSebelumnya is SuratNextPrevResponse)
            suratSebelumnya.toSuratNextPrev() else suratSebelumnya
    )
}