package com.me.myquran.domain.repository

import com.me.myquran.domain.model.DaftarSurat
import com.me.myquran.domain.model.DetailSurat
import com.me.myquran.domain.model.DetailTafsir
import com.me.myquran.utils.Resource
import com.me.myquran.utils.WrappedListResponse
import kotlinx.coroutines.flow.Flow

interface MyQuranRepository {
    suspend fun getDetailSurat(nomor: Int): Flow<Resource<DetailSurat>>
    suspend fun getDetailTafsir(nomor: Int): Flow<Resource<DetailTafsir>>
    suspend fun getDaftarSurat(fetchFromRemote: Boolean): Flow<Resource<List<DaftarSurat>>>
}