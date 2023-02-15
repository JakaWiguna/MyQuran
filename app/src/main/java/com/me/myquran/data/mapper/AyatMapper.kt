package com.me.myquran.data.mapper

import com.me.myquran.data.remote.dto.AyatResponse
import com.me.myquran.domain.model.Ayat

fun AyatResponse.toAyat(): Ayat {
    return Ayat(
        teksArab = teksArab,
        teksLatin = teksLatin,
        nomorAyat = nomorAyat,
        audio = audio?.toAudio(),
        teksIndonesia = teksIndonesia
    )
}