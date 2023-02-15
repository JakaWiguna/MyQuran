package com.me.myquran.data.repository

import com.me.myquran.data.local.MyQuranDatabase
import com.me.myquran.data.mapper.*
import com.me.myquran.data.remote.api.EQuranApi
import com.me.myquran.domain.model.DaftarSurat
import com.me.myquran.domain.model.DetailSurat
import com.me.myquran.domain.model.DetailTafsir
import com.me.myquran.domain.repository.MyQuranRepository
import com.me.myquran.utils.DispatcherProvider
import com.me.myquran.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MyQuranRepositoryImpl @Inject constructor(
    private val api: EQuranApi,
    private val db: MyQuranDatabase,
    private val dispatcherProvider: DispatcherProvider,
) : MyQuranRepository {
    private val daftarSuratDao = db.daftarSuratDao
    private val audioDao = db.audioDao
    override suspend fun getDaftarSurat(
        fetchFromRemote: Boolean,
    ): Flow<Resource<List<DaftarSurat>>> {
        return flow {
            emit(Resource.Loading(true))
            val localDaftarSuratAndAudio = daftarSuratDao.getDaftarSuratEntities()
            emit(Resource.Success(data = localDaftarSuratAndAudio.map {
                it.toDaftarSurat()
            }))
            val shouldJustLoadFromCache = localDaftarSuratAndAudio.isNotEmpty() && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            try {
                val response = api.getDaftarSurat()
                response.let { resp ->
                    if (resp.isSuccessful) {
                        val daftarSuratResponse = resp.body()!!
                        val daftarSurat = daftarSuratResponse.data?.map { it.toDaftarSurat() }
                        daftarSurat?.let {
                            daftarSuratDao.clearDaftarSuratEntities()
                            daftarSuratDao.insertDaftarSuratEntities(
                                it.map { daftarSurat -> daftarSurat.toDaftarSuratEntity() }
                            )
                            audioDao.insertAudioEntities(
                                it.map { daftarSurat -> daftarSurat.toAudioEntity() }
                            )
                        }
                        emit(Resource.Success(
                            data = daftarSuratDao.getDaftarSuratEntities()
                                .map { it.toDaftarSurat() }
                        ))
                    } else {
                        emit(Resource.Error(response.errorBody()!!.string()))
                    }
                }
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            }
            emit(Resource.Loading(false))
        }.flowOn(dispatcherProvider.io)
    }

    override suspend fun getDetailSurat(nomor: Int): Flow<Resource<DetailSurat>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val response = api.getDetailSurat(nomor)
                response.let { resp ->
                    if (resp.isSuccessful) {
                        val detailSuratResponse = resp.body()!!
                        emit(Resource.Success(detailSuratResponse.data?.toDetailSurat()))
                    } else {
                        emit(Resource.Error(response.errorBody()!!.string()))
                    }
                }
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            }
            emit(Resource.Loading(false))
        }.flowOn(dispatcherProvider.io)
    }

    override suspend fun getDetailTafsir(nomor: Int): Flow<Resource<DetailTafsir>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val response = api.getDetailTafsir(nomor)
                response.let { resp ->
                    if (resp.isSuccessful) {
                        val detailSuratResponse = resp.body()!!
                        emit(Resource.Success(detailSuratResponse.data?.toDetailTafsir()))
                    } else {
                        emit(Resource.Error(response.errorBody()!!.string()))
                    }
                }
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            }
            emit(Resource.Loading(false))
        }.flowOn(dispatcherProvider.io)
    }
}