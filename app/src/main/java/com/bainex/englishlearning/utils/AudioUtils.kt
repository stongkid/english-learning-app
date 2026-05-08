
package com.bainex.englishlearning.utils

import android.media.MediaMetadataRetriever
import java.io.File

object AudioUtils {

    fun getAudioDuration(filePath: String): Long {
        return try {
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(filePath)
            val durationStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
            retriever.release()
            durationStr?.toLong() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    fun formatDuration(milliseconds: Long): String {
        val seconds = (milliseconds / 1000) % 60
        val minutes = (milliseconds / 1000) / 60
        return String.format("%d:%02d", minutes, seconds)
    }

    fun isAudioFile(file: File): Boolean {
        val extension = file.extension.lowercase()
        return listOf("mp3", "wav", "ogg", "flac", "m4a").contains(extension)
    }
}
