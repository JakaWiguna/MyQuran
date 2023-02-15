package com.me.myquran.data.remote.api

import com.me.myquran.data.remote.dto.DaftarSuratResponse
import com.me.myquran.data.remote.dto.DetailSuratResponse
import com.me.myquran.data.remote.dto.DetailTafsirResponse
import com.me.myquran.utils.WrappedListResponse
import com.me.myquran.utils.WrappedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EQuranApi {

    @GET("surat")
    suspend fun getDaftarSurat(): Response<WrappedListResponse<DaftarSuratResponse>>

    @GET("surat/{nomor}")
    suspend fun getDetailSurat(@Path("nomor") nomor: Int): Response<WrappedResponse<DetailSuratResponse>>

    @GET("tafsir/{nomor}")
    suspend fun getDetailTafsir(@Path("nomor") nomor: Int): Response<WrappedResponse<DetailTafsirResponse>>

}