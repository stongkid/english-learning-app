
package com.example.englishlearning.data.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("/api/transcribe")
    suspend fun transcribeAudio(@Body request: TranscribeRequest): TranscribeResponse

    @GET("/api/word/{word}")
    suspend fun getWordDefinition(@Path("word") word: String): WordDefinitionResponse
}

data class TranscribeRequest(
    val audioBase64: String
)

data class TranscribeResponse(
    val success: Boolean,
    val data: TranscribeData,
    val message: String
)

data class TranscribeData(
    val transcript: String
)

data class WordDefinitionResponse(
    val success: Boolean,
    val data: WordDefinitionData,
    val message: String
)

data class WordDefinitionData(
    val word: String,
    val phonetic: String,
    val meaning: String,
    val partOfSpeech: String,
    val example: String
)
