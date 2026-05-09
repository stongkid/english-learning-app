
package com.bainex.englishlearning.data.repository

import com.bainex.englishlearning.data.dao.AnnotationDao
import com.bainex.englishlearning.data.dao.ReadingDao
import com.bainex.englishlearning.data.model.WordAnnotation
import com.bainex.englishlearning.data.model.Reading
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadingRepository @Inject constructor(
    private val readingDao: ReadingDao,
    private val annotationDao: AnnotationDao
) {
    fun getAllReading(): Flow<List<Reading>> = readingDao.getAllReading()

    suspend fun getReadingById(id: Long): Reading? = readingDao.getReadingById(id)

    fun searchReading(query: String): Flow<List<Reading>> = readingDao.searchReading("%$query%")

    suspend fun insertReading(reading: Reading): Long = readingDao.insertReading(reading)

    suspend fun updateReading(reading: Reading) = readingDao.updateReading(reading)

    suspend fun deleteReading(id: Long) = readingDao.deleteReading(id)

    fun getAnnotationsByReadingId(readingId: Long): Flow<List<WordAnnotation>> =
        annotationDao.getAnnotationsByReadingId(readingId)

    suspend fun insertAnnotation(annotation: WordAnnotation): Long = annotationDao.insertAnnotation(annotation)

    suspend fun updateAnnotation(annotation: WordAnnotation) = annotationDao.updateAnnotation(annotation)

    suspend fun deleteAnnotation(id: Long) = annotationDao.deleteAnnotation(id)
}
