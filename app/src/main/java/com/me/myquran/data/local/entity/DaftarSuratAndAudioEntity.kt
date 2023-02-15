package com.me.myquran.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class DaftarSuratAndAudioEntity(
    @Embedded val daftarSuratEntity: DaftarSuratEntity,
    @Relation(
         parentColumn = "nomor",
         entityColumn = "dataSuratNomor"
    )
    val audioEntity: AudioEntity
)
