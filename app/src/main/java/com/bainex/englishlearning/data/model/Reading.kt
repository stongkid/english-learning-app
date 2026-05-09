
package com.bainex.englishlearning.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reading")
data class Reading(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String? = null,
    val content: String,
    val sourceType: Int,
    val sourcePath: String? = null,
    val progress: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
) {
    companion object {
        const val SOURCE_CAMERA = 1
        const val SOURCE_PDF = 2
        const val SOURCE_TEXT = 3
    }
}
