
package com.example.englishlearning.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishlearning.data.model.Annotation
import com.example.englishlearning.data.model.Reading
import com.example.englishlearning.data.repository.ReadingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReadingViewModel @Inject constructor(
    private val readingRepository: ReadingRepository
) : ViewModel() {

    private val _readingList = MutableStateFlow<List<Reading>>(emptyList())
    val readingList: StateFlow<List<Reading>> = _readingList

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        loadReadingList()
    }

    fun loadReadingList() {
        viewModelScope.launch {
            readingRepository.getAllReading().collect {
                _readingList.value = it
            }
        }
    }

    fun searchReading(query: String) {
        _searchQuery.value = query
        if (query.isEmpty()) {
            loadReadingList()
        } else {
            viewModelScope.launch {
                readingRepository.searchReading(query).collect {
                    _readingList.value = it
                }
            }
        }
    }

    fun addReading(reading: Reading) {
        viewModelScope.launch(Dispatchers.IO) {
            readingRepository.insertReading(reading)
        }
    }

    fun updateReading(reading: Reading) {
        viewModelScope.launch(Dispatchers.IO) {
            readingRepository.updateReading(reading)
        }
    }

    fun deleteReading(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            readingRepository.deleteReading(id)
        }
    }

    suspend fun getReadingById(id: Long): Reading? {
        return withContext(Dispatchers.IO) {
            readingRepository.getReadingById(id)
        }
    }

    fun getAnnotationsByReadingId(readingId: Long) =
        readingRepository.getAnnotationsByReadingId(readingId)

    fun addAnnotation(annotation: Annotation) {
        viewModelScope.launch(Dispatchers.IO) {
            readingRepository.insertAnnotation(annotation)
        }
    }

    fun updateAnnotation(annotation: Annotation) {
        viewModelScope.launch(Dispatchers.IO) {
            readingRepository.updateAnnotation(annotation)
        }
    }

    fun deleteAnnotation(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            readingRepository.deleteAnnotation(id)
        }
    }
}
