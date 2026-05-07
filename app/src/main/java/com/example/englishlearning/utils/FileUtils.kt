
package com.example.englishlearning.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object FileUtils {

    fun getFileNameFromUri(context: Context, uri: Uri): String {
        var fileName = "unknown"
        context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex("_display_name")
            if (cursor.moveToFirst() && nameIndex != -1) {
                fileName = cursor.getString(nameIndex)
            }
        }
        return fileName
    }

    fun copyFileToAppDir(context: Context, uri: Uri, destinationDir: File): File? {
        return try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            inputStream?.use { input ->
                val fileName = getFileNameFromUri(context, uri)
                val destFile = File(destinationDir, fileName)
                FileOutputStream(destFile).use { output ->
                    input.copyTo(output)
                }
                destFile
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getAppFilesDir(context: Context): File {
        val dir = File(context.filesDir, "learning_data")
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir
    }

    fun getAudioDir(context: Context): File {
        val dir = File(getAppFilesDir(context), "audio")
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir
    }

    fun getReadingDir(context: Context): File {
        val dir = File(getAppFilesDir(context), "reading")
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir
    }

    fun deleteFile(filePath: String): Boolean {
        val file = File(filePath)
        return file.exists() && file.delete()
    }
}
