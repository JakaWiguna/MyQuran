package com.me.myquran.data.local.dao
import androidx.room.*
import com.me.myquran.data.local.entity.DaftarSuratAndAudioEntity
import com.me.myquran.data.local.entity.DaftarSuratEntity

@Dao
interface DaftarSuratDao {

    @Transaction
    @Query("SELECT * FROM DaftarSuratEntity")
    fun getDaftarSuratEntities(): List<DaftarSuratAndAudioEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDaftarSuratEntities(
        daftarSuratEntities: List<DaftarSuratEntity>
    )

    @Query("DELETE FROM DaftarSuratEntity")
    suspend fun clearDaftarSuratEntities()

    @Query("SELECT * FROM DaftarSuratEntity WHERE nomor = :nomor")
    suspend fun getDaftarSuratEntityById(nomor: Int?): DaftarSuratEntity?

    @Delete
    suspend fun deleteNote(note: DaftarSuratEntity)

}
