package com.me.myquran.data.mapper

import com.me.myquran.data.local.entity.AudioEntity
import com.me.myquran.data.local.entity.DaftarSuratAndAudioEntity
import com.me.myquran.data.local.entity.DaftarSuratEntity
import com.me.myquran.data.remote.dto.DaftarSuratResponse
import com.me.myquran.domain.model.DaftarSurat

fun DaftarSuratResponse.toDaftarSurat(): DaftarSurat {
    return DaftarSurat(
        nomor = nomor,
        nama = nama,
        namaLatin = namaLatin,
        jumlahAyat = jumlahAyat,
        tempatTurun = tempatTurun,
        arti = arti,
        deskripsi = deskripsi,
        audio = audio?.toAudio()
    )
}

fun DaftarSurat.toDaftarSuratEntity(): DaftarSuratEntity {
    return DaftarSuratEntity(
        nomor = nomor!!,
        nama = nama,
        namaLatin = namaLatin,
        jumlahAyat = jumlahAyat,
        tempatTurun = tempatTurun,
        arti = arti,
        deskripsi = deskripsi,
    )
}

fun DaftarSurat.toAudioEntity(): AudioEntity {
    return AudioEntity(
        audio01 = audio?.audio01,
        audio02 = audio?.audio02,
        audio03 = audio?.audio03,
        audio04 = audio?.audio04,
        audio05 = audio?.audio05,
        dataSuratNomor = nomor!!
    )
}

fun DaftarSuratAndAudioEntity.toDaftarSurat(): DaftarSurat {
    return DaftarSurat(
        nomor = daftarSuratEntity.nomor,
        nama = daftarSuratEntity.nama,
        namaLatin = daftarSuratEntity.namaLatin,
        jumlahAyat = daftarSuratEntity.jumlahAyat,
        tempatTurun = daftarSuratEntity.tempatTurun,
        arti = daftarSuratEntity.arti,
        deskripsi = daftarSuratEntity.deskripsi,
        audio = audioEntity.toAudio()
    )
}