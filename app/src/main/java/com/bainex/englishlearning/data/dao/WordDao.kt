
package com.bainex.englishlearning.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bainex.englishlearning.data.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM word ORDER BY word ASC")
    fun getAllWords(): Flow<List<Word>>

    @Query("SELECT * FROM word WHERE id = :id")
    suspend fun getWordById(id: Long): Word?

    @Query("SELECT * FROM word WHERE word = :word")
    suspend fun getWordByWord(word: String): Word?

    @Query("SELECT * FROM word WHERE word LIKE :query ORDER BY word ASC")
    fun searchWords(query: String): Flow<List<Word>>

    @Insert
    suspend fun insertWord(word: Word): Long

    @Insert
    suspend fun insertWords(words: List<Word>)

    @Update
    suspend fun updateWord(word: Word)

    @Query("DELETE FROM word WHERE id = :id")
    suspend fun deleteWord(id: Long)

    @Query("SELECT COUNT(*) FROM word")
    suspend fun getWordCount(): Int
}
