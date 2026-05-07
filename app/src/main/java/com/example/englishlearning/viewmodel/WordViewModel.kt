
package com.example.englishlearning.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishlearning.data.model.Word
import com.example.englishlearning.data.model.WordLearning
import com.example.englishlearning.data.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WordViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : ViewModel() {

    private val _wordList = MutableStateFlow<List<Word>>(emptyList())
    val wordList: StateFlow<List<Word>> = _wordList

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _wordsForReview = MutableStateFlow<List<WordLearning>>(emptyList())
    val wordsForReview: StateFlow<List<WordLearning>> = _wordsForReview

    init {
        loadWordList()
        loadWordsForReview()
    }

    fun loadWordList() {
        viewModelScope.launch {
            wordRepository.getAllWords().collect {
                _wordList.value = it
            }
        }
    }

    fun searchWords(query: String) {
        _searchQuery.value = query
        if (query.isEmpty()) {
            loadWordList()
        } else {
            viewModelScope.launch {
                wordRepository.searchWords(query).collect {
                    _wordList.value = it
                }
            }
        }
    }

    fun loadWordsForReview() {
        viewModelScope.launch {
            wordRepository.getWordsForReview(System.currentTimeMillis()).collect {
                _wordsForReview.value = it
            }
        }
    }

    suspend fun getWordById(id: Long): Word? {
        return withContext(Dispatchers.IO) {
            wordRepository.getWordById(id)
        }
    }

    suspend fun getWordByWord(word: String): Word? {
        return withContext(Dispatchers.IO) {
            wordRepository.getWordByWord(word)
        }
    }

    fun updateWordLearning(wordLearning: WordLearning) {
        viewModelScope.launch(Dispatchers.IO) {
            wordRepository.updateWordLearning(wordLearning)
        }
    }

    fun recordWordEncounter(wordId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val existing = wordRepository.getWordLearningByWordId(wordId)
            if (existing != null) {
                val updated = existing.copy(
                    encounterCount = existing.encounterCount + 1,
                    lastEncounterAt = System.currentTimeMillis()
                )
                wordRepository.updateWordLearning(updated)
            } else {
                val newWordLearning = WordLearning(
                    wordId = wordId,
                    encounterCount = 1,
                    lastEncounterAt = System.currentTimeMillis(),
                    nextReviewAt = System.currentTimeMillis() + 86400000
                )
                wordRepository.insertWordLearning(newWordLearning)
            }
        }
    }

    suspend fun getWordCount(): Int {
        return withContext(Dispatchers.IO) {
            wordRepository.getWordCount()
        }
    }

    suspend fun getWordLearningCount(): Int {
        return withContext(Dispatchers.IO) {
            wordRepository.getWordLearningCount()
        }
    }

    suspend fun getMasteredWordCount(): Int {
        return withContext(Dispatchers.IO) {
            wordRepository.getMasteredWordCount()
        }
    }
}
