
package com.bainex.englishlearning.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val word: String,
    val phonetic: String? = null,
    val meaning: String,
    val partOfSpeech: String? = null,
    val example: String? = null,
    val level: Int = 1
)
