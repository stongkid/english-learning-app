
package com.bainex.englishlearning.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "annotation",
    foreignKeys = [
        ForeignKey(
            entity = Reading::class,
            parentColumns = ["id"],
            childColumns = ["readingId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class WordAnnotation(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val readingId: Long,
    val word: String,
    val definition: String? = null,
    val note: String? = null,
    val position: Int
)
