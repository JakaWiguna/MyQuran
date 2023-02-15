package com.me.myquran.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.me.myquran.data.local.dao.AudioDao
import com.me.myquran.data.local.dao.DaftarSuratDao
import com.me.myquran.data.local.entity.AudioEntity
import com.me.myquran.data.local.entity.DaftarSuratEntity

@Database(
    entities = [DaftarSuratEntity::class, AudioEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MyQuranDatabase: RoomDatabase() {
    abstract val daftarSuratDao: DaftarSuratDao
    abstract val audioDao: AudioDao
    companion object {
        const val DATABASE_NAME = "myquran_db"
    }
}