package com.me.myquran.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(
        entity = DaftarSuratEntity::class,
        parentColumns = ["nomor"],
        childColumns = ["dataSuratNomor"],
        onDelete = CASCADE
    )
])
data class AudioEntity(
    val audio01: String? = null,
    val audio02: String? = null,
    val audio03: String? = null,
    val audio04: String? = null,
    val audio05: String? = null,
    val dataSuratNomor: Int,
    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}