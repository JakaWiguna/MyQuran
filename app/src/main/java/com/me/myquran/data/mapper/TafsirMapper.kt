package com.me.myquran.data.mapper

import com.me.myquran.data.remote.dto.TafsirResponse
import com.me.myquran.domain.model.Ayat
import com.me.myquran.domain.model.Tafsir
import com.me.myquran.utils.CommonUtils

fun TafsirResponse.toTafsir(): Tafsir {
    return Tafsir(
        ayat = ayat,
        teks = teks
    )
}