package com.me.myquran.domain.use_case

import com.me.myquran.data.repository.MyQuranRepositoryImpl
import com.me.myquran.domain.model.DetailSurat
import com.me.myquran.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetailSurat @Inject constructor(private val repository: MyQuranRepositoryImpl) {
    suspend fun execute(id: Int):Flow<Resource<DetailSurat>>{
        return repository.getDetailSurat(id)
    }
}