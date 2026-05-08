
package com.bainex.englishlearning.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bainex.englishlearning.data.model.Audio
import com.bainex.englishlearning.data.repository.AudioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AudioViewModel @Inject constructor(
    private val audioRepository: AudioRepository
) : ViewModel() {

    private val _audioList = MutableStateFlow<List<Audio>>(emptyList())
    val audioList: StateFlow<List<Audio>> = _audioList

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        loadAudioList()
    }

    fun loadAudioList() {
        viewModelScope.launch {
            audioRepository.getAllAudio().collect {
                _audioList.value = it
            }
        }
    }

    fun searchAudio(query: String) {
        _searchQuery.value = query
        if (query.isEmpty()) {
            loadAudioList()
        } else {
            viewModelScope.launch {
                audioRepository.searchAudio(query).collect {
                    _audioList.value = it
                }
            }
        }
    }

    fun addAudio(audio: Audio) {
        viewModelScope.launch(Dispatchers.IO) {
            audioRepository.insertAudio(audio)
        }
    }

    fun updateAudio(audio: Audio) {
        viewModelScope.launch(Dispatchers.IO) {
            audioRepository.updateAudio(audio)
        }
    }

    fun deleteAudio(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            audioRepository.deleteAudio(id)
        }
    }

    suspend fun getAudioById(id: Long): Audio? {
        return withContext(Dispatchers.IO) {
            audioRepository.getAudioById(id)
        }
    }
}
