
package com.bainex.englishlearning.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bainex.englishlearning.data.model.Reading
import kotlinx.coroutines.flow.Flow

@Dao
interface ReadingDao {
    @Query("SELECT * FROM reading ORDER BY createdAt DESC")
    fun getAllReading(): Flow<List<Reading>>

    @Query("SELECT * FROM reading WHERE id = :id")
    suspend fun getReadingById(id: Long): Reading?

    @Query("SELECT * FROM reading WHERE title LIKE :query")
    fun searchReading(query: String): Flow<List<Reading>>

    @Insert
    suspend fun insertReading(reading: Reading): Long

    @Update
    suspend fun updateReading(reading: Reading)

    @Query("DELETE FROM reading WHERE id = :id")
    suspend fun deleteReading(id: Long)
}
