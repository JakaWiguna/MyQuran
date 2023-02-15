package com.me.myquran.data.local.dao
import androidx.room.*
import com.me.myquran.data.local.entity.AudioEntity

@Dao
interface AudioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAudioEntities(
        audioEntities: List<AudioEntity>
    )

    @Query("SELECT * FROM AudioEntity WHERE id = :id")
    suspend fun getAudioById(id: Int?): AudioEntity?

    @Delete
    suspend fun deleteAudio(note: AudioEntity)

}
