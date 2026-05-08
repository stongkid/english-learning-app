
package com.bainex.englishlearning.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bainex.englishlearning.data.dao.*
import com.bainex.englishlearning.data.model.*

@Database(
    entities = [Audio::class, Reading::class, Annotation::class, Word::class, WordLearning::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun audioDao(): AudioDao
    abstract fun readingDao(): ReadingDao
    abstract fun annotationDao(): AnnotationDao
    abstract fun wordDao(): WordDao
    abstract fun wordLearningDao(): WordLearningDao
}
