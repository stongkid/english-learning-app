
package com.bainex.englishlearning.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bainex.englishlearning.data.model.WordAnnotation
import kotlinx.coroutines.flow.Flow

@Dao
interface AnnotationDao {
    @Query("SELECT * FROM annotation WHERE readingId = :readingId")
    fun getAnnotationsByReadingId(readingId: Long): Flow<List<WordAnnotation>>

    @Query("SELECT * FROM annotation WHERE id = :id")
    suspend fun getAnnotationById(id: Long): WordAnnotation?

    @Insert
    suspend fun insertAnnotation(annotation: WordAnnotation): Long

    @Update
    suspend fun updateAnnotation(annotation: WordAnnotation)

    @Query("DELETE FROM annotation WHERE id = :id")
    suspend fun deleteAnnotation(id: Long)

    @Query("DELETE FROM annotation WHERE readingId = :readingId")
    suspend fun deleteAnnotationsByReadingId(readingId: Long)
}
