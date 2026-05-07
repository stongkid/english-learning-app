
package com.example.englishlearning.data.repository

import com.example.englishlearning.data.dao.AudioDao
import com.example.englishlearning.data.model.Audio
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AudioRepository @Inject constructor(
    private val audioDao: AudioDao
) {
    fun getAllAudio(): Flow<List<Audio>> = audioDao.getAllAudio()

    suspend fun getAudioById(id: Long): Audio? = audioDao.getAudioById(id)

    fun searchAudio(query: String): Flow<List<Audio>> = audioDao.searchAudio("%$query%")

    suspend fun insertAudio(audio: Audio): Long = audioDao.insertAudio(audio)

    suspend fun updateAudio(audio: Audio) = audioDao.updateAudio(audio)

    suspend fun deleteAudio(id: Long) = audioDao.deleteAudio(id)
}
