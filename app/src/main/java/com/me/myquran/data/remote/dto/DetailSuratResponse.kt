package com.me.myquran.data.remote.dto

import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DetailSuratResponse(

    @field:SerializedName("jumlahAyat")
    @field:Expose
    val jumlahAyat: Int? = null,

    @field:SerializedName("nama")
    @field:Expose
    val nama: String? = null,

    @field:SerializedName("audioFull")
    @field:Expose
    val audioFull: AudioResponse? = null,

    @field:SerializedName("suratSebelumnya")
    @field:Expose
    val suratSebelumnya: Any? = null,

    @field:SerializedName("tempatTurun")
    @field:Expose
    val tempatTurun: String? = null,

    @field:SerializedName("ayat")
    @field:Expose
    val ayat: List<AyatResponse?>? = null,

    @field:SerializedName("arti")
    @field:Expose
    val arti: String? = null,

    @field:SerializedName("deskripsi")
    @field:Expose
    val deskripsi: String? = null,

    @field:SerializedName("nomor")
    @field:Expose
    val nomor: Int? = null,

    @field:SerializedName("namaLatin")
    @field:Expose
    val namaLatin: String? = null,

    @field:SerializedName("suratSelanjutnya")
    @field:Expose
    val suratSelanjutnya: Any? = null,
) {
    val isSuratSelanjutnyaBoolean: Boolean
        get() = suratSelanjutnya is Boolean

    val suratSelanjutnyaResponse: SuratNextPrevResponse?
        get() = if (!isSuratSelanjutnyaBoolean) {
            Gson().fromJson(suratSelanjutnya.toString(), SuratNextPrevResponse::class.java)
        } else {
            null
        }

    val isSuratSebelumnyaBoolean: Boolean
        get() = suratSebelumnya is Boolean

    val suratSebelumnyaResponse: SuratNextPrevResponse?
        get() = if (!isSuratSebelumnyaBoolean) {
            Gson().fromJson(suratSebelumnya.toString(), SuratNextPrevResponse::class.java)
        } else {
            null
        }
}