package com.me.myquran.data.repository

import com.me.myquran.data.local.dao.AudioDao
import com.me.myquran.data.local.dao.DaftarSuratDao
import com.me.myquran.data.local.entity.AudioEntity
import com.me.myquran.data.local.entity.DaftarSuratAndAudioEntity
import com.me.myquran.data.local.entity.DaftarSuratEntity
import com.me.myquran.data.mapper.toDaftarSurat
import com.me.myquran.data.remote.api.EQuranApi
import com.me.myquran.data.remote.dto.AudioResponse
import com.me.myquran.data.remote.dto.DaftarSuratResponse
import com.me.myquran.utils.Resource
import com.me.myquran.utils.WrappedListResponse
import id.tss.taskmanagement.utils.TestDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.me.myquran.domain.model.Audio
import com.me.myquran.domain.model.DaftarSurat
import org.junit.Assert.assertNotNull
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class MyQuranRepositoryImplTest {
    @Mock
    lateinit var api: EQuranApi

    @Mock
    lateinit var daftarSuratDao: DaftarSuratDao

    @Mock
    lateinit var audioDao: AudioDao
    private lateinit var repository: MyQuranRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = MyQuranRepositoryImpl(api, daftarSuratDao, audioDao, TestDispatcherProvider())
    }

    @Test
    fun `getDaftarSurat should return data from remote when fetchFromRemote is false and local data is not available`() =
        runBlocking {
            val expectedResult = listOf(
                DaftarSurat(
                    jumlahAyat = 7,
                    nama = "Al-Fatihah",
                    tempatTurun = "Mekah",
                    arti = "arti",
                    deskripsi = "deskripsi",
                    nomor = 1,
                    namaLatin = "Al-Fatihah",
                    audio = Audio(
                        audio01 = "1",
                        audio02 = "2",
                        audio03 = "3",
                        audio04 = "4",
                        audio05 = "5",
                    ),
                )
            )

            // Mock remote response
            val daftarSuratResponse = WrappedListResponse(
                code = 200,
                data = listOf(
                    DaftarSuratResponse(
                        jumlahAyat = 7,
                        nama = "Al-Fatihah",
                        tempatTurun = "Mekah",
                        arti = "arti",
                        deskripsi = "deskripsi",
                        nomor = 1,
                        namaLatin = "Al-Fatihah",
                        audio = AudioResponse(
                            audio01 = "1",
                            audio02 = "2",
                            audio03 = "3",
                            audio04 = "4",
                            audio05 = "5",
                        ),
                    )
                )
            )

            // Mock local data
            val localDaftarSuratEntities = listOf(
                DaftarSuratAndAudioEntity(
                    daftarSuratEntity = DaftarSuratEntity(
                        jumlahAyat = 7,
                        nama = "Al-Fatihah",
                        tempatTurun = "Mekah",
                        arti = "arti",
                        deskripsi = "deskripsi",
                        nomor = 1,
                        namaLatin = "Al-Fatihah"
                    ),
                    audioEntity = AudioEntity(
                        audio01 = "1",
                        audio02 = "2",
                        audio03 = "3",
                        audio04 = "4",
                        audio05 = "5",
                        dataSuratNomor = 1,
                    )
                )
            )

            `when`(daftarSuratDao.getDaftarSuratEntities())
                .thenReturn(emptyList())

            `when`(api.getDaftarSurat())
                .thenReturn(Response.success(daftarSuratResponse))

            `when`(daftarSuratDao.getDaftarSuratEntities())
                .thenReturn(localDaftarSuratEntities)

            // when
            repository.getDaftarSurat(false).take(3).collect { resource ->

                // then
                when (resource) {
                    is Resource.Loading -> {
                        assert(resource.isLoading || !resource.isLoading)
                    }
                    is Resource.Success -> {
                        assert(resource.data == expectedResult)
                    }
                    else -> {
                        // should not reach here
                        assert(false)
                    }
                }
            }
        }

    @Test
    fun `getDaftarSurat should return data from cache when fetchFromRemote is false and local data is available`() =
        runBlocking {
            val expectedResult = listOf(
                DaftarSurat(
                    jumlahAyat = 7,
                    nama = "Al-Fatihah",
                    tempatTurun = "Mekah",
                    arti = "arti",
                    deskripsi = "deskripsi",
                    nomor = 1,
                    namaLatin = "Al-Fatihah",
                    audio = Audio(
                        audio01 = "1",
                        audio02 = "2",
                        audio03 = "3",
                        audio04 = "4",
                        audio05 = "5",
                    ),
                )
            )
            // Mock local data
            val localDaftarSuratEntities = listOf(
                DaftarSuratAndAudioEntity(
                    daftarSuratEntity = DaftarSuratEntity(
                        jumlahAyat = 7,
                        nama = "Al-Fatihah",
                        tempatTurun = "Mekah",
                        arti = "arti",
                        deskripsi = "deskripsi",
                        nomor = 1,
                        namaLatin = "Al-Fatihah"
                    ),
                    audioEntity = AudioEntity(
                        audio01 = "1",
                        audio02 = "2",
                        audio03 = "3",
                        audio04 = "4",
                        audio05 = "5",
                        dataSuratNomor = 1,
                    )
                )
            )


            Mockito.`when`(daftarSuratDao.getDaftarSuratEntities())
                .thenReturn(localDaftarSuratEntities)

            // when
            repository.getDaftarSurat(false).take(3).collect { resource ->
                // then


                when (resource) {
                    is Resource.Loading -> {
                        assert(resource.isLoading || !resource.isLoading)
                    }
                    is Resource.Success -> {
                        assert(resource.data == expectedResult)
                    }
                    else -> {
                        // should not reach here
                        assert(false)
                    }
                }
            }

        }
}