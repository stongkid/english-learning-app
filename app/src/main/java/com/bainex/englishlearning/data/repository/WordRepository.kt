
package com.bainex.englishlearning.data.repository

import com.bainex.englishlearning.data.dao.WordDao
import com.bainex.englishlearning.data.dao.WordLearningDao
import com.bainex.englishlearning.data.model.Word
import com.bainex.englishlearning.data.model.WordLearning
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WordRepository @Inject constructor(
    private val wordDao: WordDao,
    private val wordLearningDao: WordLearningDao
) {
    fun getAllWords(): Flow<List<Word>> = wordDao.getAllWords()

    suspend fun getWordById(id: Long): Word? = wordDao.getWordById(id)

    suspend fun getWordByWord(word: String): Word? = wordDao.getWordByWord(word)

    fun searchWords(query: String): Flow<List<Word>> = wordDao.searchWords("%$query%")

    suspend fun insertWord(word: Word): Long = wordDao.insertWord(word)

    suspend fun insertWords(words: List<Word>) = wordDao.insertWords(words)

    fun getAllWordLearning(): Flow<List<WordLearning>> = wordLearningDao.getAllWordLearning()

    suspend fun getWordLearningByWordId(wordId: Long): WordLearning? =
        wordLearningDao.getWordLearningByWordId(wordId)

    fun getWordsForReview(currentTime: Long): Flow<List<WordLearning>> =
        wordLearningDao.getWordsForReview(currentTime)

    suspend fun insertWordLearning(wordLearning: WordLearning): Long =
        wordLearningDao.insertWordLearning(wordLearning)

    suspend fun updateWordLearning(wordLearning: WordLearning) =
        wordLearningDao.updateWordLearning(wordLearning)

    suspend fun getWordCount(): Int = wordDao.getWordCount()

    suspend fun getWordLearningCount(): Int = wordLearningDao.getWordLearningCount()

    suspend fun getMasteredWordCount(): Int = wordLearningDao.getMasteredWordCount()
}
