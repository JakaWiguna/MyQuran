package com.me.myquran.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class TafsirResponse(

    @field:SerializedName("ayat")
    @field:Expose
    val ayat: Int? = null,

    @field:SerializedName("teks")
    @field:Expose
    val teks: String? = null

)