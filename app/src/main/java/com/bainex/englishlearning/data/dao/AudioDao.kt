
package com.bainex.englishlearning.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bainex.englishlearning.data.model.Audio
import kotlinx.coroutines.flow.Flow

@Dao
interface AudioDao {
    @Query("SELECT * FROM audio ORDER BY createdAt DESC")
    fun getAllAudio(): Flow<List<Audio>>

    @Query("SELECT * FROM audio WHERE id = :id")
    suspend fun getAudioById(id: Long): Audio?

    @Query("SELECT * FROM audio WHERE name LIKE :query")
    fun searchAudio(query: String): Flow<List<Audio>>

    @Insert
    suspend fun insertAudio(audio: Audio): Long

    @Update
    suspend fun updateAudio(audio: Audio)

    @Query("DELETE FROM audio WHERE id = :id")
    suspend fun deleteAudio(id: Long)
}
