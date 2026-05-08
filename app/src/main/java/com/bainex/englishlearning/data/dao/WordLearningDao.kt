
package com.bainex.englishlearning.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bainex.englishlearning.data.model.WordLearning
import kotlinx.coroutines.flow.Flow

@Dao
interface WordLearningDao {
    @Query("SELECT * FROM word_learning ORDER BY lastEncounterAt DESC")
    fun getAllWordLearning(): Flow<List<WordLearning>>

    @Query("SELECT * FROM word_learning WHERE wordId = :wordId")
    suspend fun getWordLearningByWordId(wordId: Long): WordLearning?

    @Query("SELECT * FROM word_learning WHERE masteryLevel < 5 AND nextReviewAt <= :currentTime ORDER BY nextReviewAt ASC")
    fun getWordsForReview(currentTime: Long): Flow<List<WordLearning>>

    @Query("SELECT wl.* FROM word_learning wl JOIN word w ON wl.wordId = w.id WHERE w.word LIKE :query")
    fun searchWordLearning(query: String): Flow<List<WordLearning>>

    @Query("SELECT COUNT(*) FROM word_learning")
    suspend fun getWordLearningCount(): Int

    @Query("SELECT COUNT(*) FROM word_learning WHERE masteryLevel = 5")
    suspend fun getMasteredWordCount(): Int

    @Insert
    suspend fun insertWordLearning(wordLearning: WordLearning): Long

    @Update
    suspend fun updateWordLearning(wordLearning: WordLearning)

    @Query("DELETE FROM word_learning WHERE id = :id")
    suspend fun deleteWordLearning(id: Long)

    @Query("DELETE FROM word_learning WHERE wordId = :wordId")
    suspend fun deleteWordLearningByWordId(wordId: Long)
}
