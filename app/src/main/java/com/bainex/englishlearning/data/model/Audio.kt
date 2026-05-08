
package com.bainex.englishlearning.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audio")
data class Audio(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val path: String,
    val duration: Long? = null,
    val transcript: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
