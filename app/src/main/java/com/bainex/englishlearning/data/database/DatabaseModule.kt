
package com.bainex.englishlearning.data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "english_learning.db"
        ).build()
    }

    @Provides
    fun provideAudioDao(database: AppDatabase) = database.audioDao()

    @Provides
    fun provideReadingDao(database: AppDatabase) = database.readingDao()

    @Provides
    fun provideAnnotationDao(database: AppDatabase) = database.annotationDao()

    @Provides
    fun provideWordDao(database: AppDatabase) = database.wordDao()

    @Provides
    fun provideWordLearningDao(database: AppDatabase) = database.wordLearningDao()
}
