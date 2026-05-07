
package com.example.englishlearning.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "word_learning",
    foreignKeys = [
        ForeignKey(
            entity = Word::class,
            parentColumns = ["id"],
            childColumns = ["wordId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class WordLearning(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val wordId: Long,
    val encounterCount: Int = 1,
    val lastEncounterAt: Long? = null,
    val masteryLevel: Int = 0,
    val nextReviewAt: Long? = null,
    val createdAt: Long = System.currentTimeMillis()
)
