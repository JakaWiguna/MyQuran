package com.me.myquran.data.mapper

import com.me.myquran.data.local.entity.AudioEntity
import com.me.myquran.data.remote.dto.AudioResponse
import com.me.myquran.domain.model.Audio

fun AudioResponse.toAudio(): Audio {
    return Audio(
        audio01 = audio01,
        audio02 = audio02,
        audio03 = audio03,
        audio04 = audio04,
        audio05 = audio05
    )
}

fun AudioEntity.toAudio(): Audio {
    return Audio(
        audio01 = audio01,
        audio02 = audio02,
        audio03 = audio03,
        audio04 = audio04,
        audio05 = audio05
    )
}