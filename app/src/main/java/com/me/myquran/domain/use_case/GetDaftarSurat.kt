package com.me.myquran.domain.use_case

import com.me.myquran.data.repository.MyQuranRepositoryImpl
import com.me.myquran.domain.model.DaftarSurat
import com.me.myquran.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDaftarSurat @Inject constructor(private val repository: MyQuranRepositoryImpl) {
    suspend fun execute(fetchFromRemote: Boolean): Flow<Resource<List<DaftarSurat>>> {
        return repository.getDaftarSurat(fetchFromRemote)
    }
}