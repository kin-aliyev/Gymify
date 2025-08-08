package com.example.gymify.settings.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.graphics.scale
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject
import kotlin.math.abs

class ImageSaver @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        // Общие настройки сжатия
        private const val COMPRESSION_QUALITY = 80

        // Настройки для профиля (квадратные/портретные)
        private const val PROFILE_PICTURES_DIR = "profile_pictures"
        private const val PROFILE_PICTURE_PREFIX = "profile_picture_"
        private const val PROFILE_MAX_WIDTH = 1024
        private const val PROFILE_MAX_HEIGHT = 1024

        // Настройки для workout планов (горизонтальные)
        private const val WORKOUT_IMAGES_DIR = "workout_images"
        private const val WORKOUT_PLAN_PREFIX = "workout_plan_"
        private const val WORKOUT_MAX_WIDTH = 1600  // Шире для горизонтальных фото
        private const val WORKOUT_MAX_HEIGHT = 900  // 16:9 соотношение
    }

    // ===========================================
    // УНИВЕРСАЛЬНЫЕ УТИЛИТЫ
    // ===========================================

    /**
     * Загружает и сжимает изображение из URI с указанными размерами
     */
    private suspend fun loadAndCompressImage(
        uri: Uri,
        maxWidth: Int,
        maxHeight: Int
    ): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                val inputStream = context.contentResolver.openInputStream(uri)
                inputStream?.use { stream ->
                    // Анализ размеров без загрузки в память
                    val options = BitmapFactory.Options().apply {
                        inJustDecodeBounds = true
                    }
                    BitmapFactory.decodeStream(stream, null, options)

                    // Вычисляем коэффициент масштабирования
                    val scaleFactor = calculateInSampleSize(options, maxWidth, maxHeight)

                    // Загружаем с масштабированием
                    val inputStream2 = context.contentResolver.openInputStream(uri)
                    inputStream2?.use { stream2 ->
                        val loadOptions = BitmapFactory.Options().apply {
                            inSampleSize = scaleFactor
                            inJustDecodeBounds = false
                        }

                        val bitmap = BitmapFactory.decodeStream(stream2, null, loadOptions)
                        bitmap?.let { resizeBitmapIfNeeded(it, maxWidth, maxHeight) }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }

    private fun resizeBitmapIfNeeded(bitmap: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
        val width = bitmap.width
        val height = bitmap.height

        if (width <= maxWidth && height <= maxHeight) {
            return bitmap
        }

        val aspectRatio = width.toFloat() / height.toFloat()
        val newWidth: Int
        val newHeight: Int

        // Определяем контекст использования по размерам контейнера
        val isSquareContainer = maxWidth == maxHeight
        val isWideContainer = maxWidth > maxHeight

        when {
            // Квадратный контейнер (профиль) - вписываем изображение
            isSquareContainer -> {
                if (aspectRatio > 1.0f) { // Горизонтальное → вписываем по ширине
                    newWidth = maxWidth
                    newHeight = (maxWidth / aspectRatio).toInt()
                } else { // Вертикальное → вписываем по высоте
                    newHeight = maxHeight
                    newWidth = (maxHeight * aspectRatio).toInt()
                }
            }

            // Широкий контейнер (workout планы)
            isWideContainer -> {
                if (aspectRatio > 1.0f) { // Горизонтальное изображение
                    newWidth = maxWidth
                    newHeight = (maxWidth / aspectRatio).toInt()
                    // Проверяем что высота не превышает лимит
                    if (newHeight > maxHeight) {
                        val correctedHeight = maxHeight
                        val correctedWidth = (maxHeight * aspectRatio).toInt()
                        return bitmap.scale(correctedWidth, correctedHeight, filter = true).also {
                            if (it != bitmap) bitmap.recycle()
                        }
                    }
                } else { // Вертикальное изображение в горизонтальном контейнере
                    newHeight = maxHeight
                    newWidth = (maxHeight * aspectRatio).toInt()
                }
            }

            // Остальные случаи - стандартная логика
            else -> {
                if (width > height) {
                    newWidth = maxWidth
                    newHeight = (maxWidth / aspectRatio).toInt()
                } else {
                    newHeight = maxHeight
                    newWidth = (maxHeight * aspectRatio).toInt()
                }
            }
        }

        val resizedBitmap = bitmap.scale(newWidth, newHeight, filter = true)

        if (resizedBitmap != bitmap) {
            bitmap.recycle()
        }

        return resizedBitmap
    }

    /**
     * Сохраняет Bitmap в указанную директорию с заданным именем
     */
    private suspend fun saveBitmapToFile(
        bitmap: Bitmap,
        directory: File,
        fileName: String
    ): String? {
        return withContext(Dispatchers.IO) {
            try {
                if (!directory.exists()) {
                    directory.mkdirs()
                }

                val imageFile = File(directory, fileName)

                imageFile.outputStream().use { outputStream ->
                    bitmap.compress(
                        Bitmap.CompressFormat.JPEG,
                        COMPRESSION_QUALITY,
                        outputStream
                    )
                }

                bitmap.recycle()
                imageFile.absolutePath
            } catch (e: Exception) {
                e.printStackTrace()
                bitmap.recycle()
                null
            }
        }
    }

    /**
     * Удаляет файлы по префиксу в указанной директории
     */
    private suspend fun deleteFilesByPrefix(directoryName: String, prefix: String) {
        withContext(Dispatchers.IO) {
            try {
                val directory = File(context.filesDir, directoryName)
                if (directory.exists() && directory.isDirectory) {
                    directory.listFiles()?.forEach { file ->
                        if (file.name.startsWith(prefix)) {
                            file.delete()
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // ===========================================
    // МЕТОДЫ ДЛЯ ПРОФИЛЯ (с автоудалением)
    // ===========================================

    /**
     * Сохраняет фото профиля, удаляя предыдущие
     * Вписывает изображение в квадрат, сохраняя пропорции
     */
    suspend fun saveProfilePicture(uri: Uri): String? {
        return withContext(Dispatchers.IO) {
            try {
                // Удаляем все предыдущие фото профиля
                deleteFilesByPrefix(PROFILE_PICTURES_DIR, PROFILE_PICTURE_PREFIX)

                // Сжимаем изображение для профиля (квадратное)
                val compressedBitmap = loadAndCompressImage(
                    uri,
                    PROFILE_MAX_WIDTH,
                    PROFILE_MAX_HEIGHT
                ) ?: return@withContext null

                // Сохраняем новое
                val directory = File(context.filesDir, PROFILE_PICTURES_DIR)
                val fileName = "${PROFILE_PICTURE_PREFIX}${System.currentTimeMillis()}.jpg"

                saveBitmapToFile(compressedBitmap, directory, fileName)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    /**
     * Сохраняет фото профиля с обрезкой до квадрата (crop по центру)
     * Использует центральную часть изображения
     */
    suspend fun saveProfilePictureCropped(uri: Uri): String? {
        return withContext(Dispatchers.IO) {
            try {
                // Удаляем все предыдущие фото профиля
                deleteFilesByPrefix(PROFILE_PICTURES_DIR, PROFILE_PICTURE_PREFIX)

                // Сжимаем изображение
                val compressedBitmap = loadAndCompressImage(
                    uri,
                    PROFILE_MAX_WIDTH,
                    PROFILE_MAX_HEIGHT
                ) ?: return@withContext null

                // Обрезаем до квадрата (1:1)
                val croppedBitmap = cropToAspectRatio(compressedBitmap, 1.0f)

                // Освобождаем оригинальный bitmap если создали новый
                if (croppedBitmap != compressedBitmap) {
                    compressedBitmap.recycle()
                }

                // Сохраняем
                val directory = File(context.filesDir, PROFILE_PICTURES_DIR)
                val fileName = "${PROFILE_PICTURE_PREFIX}${System.currentTimeMillis()}.jpg"

                saveBitmapToFile(croppedBitmap, directory, fileName)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    /**
     * Удаляет конкретное фото профиля
     */
    suspend fun deleteProfilePicture(filePath: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val file = File(filePath)
                file.exists() && file.delete()
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }

    // ===========================================
    // МЕТОДЫ ДЛЯ WORKOUT PLAN's (без автоудаления)
    // ===========================================

    /**
     * Сохраняет иконку workout плана (НЕ удаляет предыдущие)
     * Оптимизировано для горизонтальных изображений, сохраняет оригинальные пропорции
     */
    suspend fun saveWorkoutPlanIcon(uri: Uri): String? {
        return withContext(Dispatchers.IO) {
            try {
                // Сжимаем изображение для workout плана (горизонтальное)
                val compressedBitmap = loadAndCompressImage(
                    uri,
                    WORKOUT_MAX_WIDTH,
                    WORKOUT_MAX_HEIGHT
                ) ?: return@withContext null

                // Сохраняем новое (БЕЗ удаления старых)
                val directory = File(context.filesDir, WORKOUT_IMAGES_DIR)
                val fileName = "${WORKOUT_PLAN_PREFIX}${System.currentTimeMillis()}.jpg"

                saveBitmapToFile(compressedBitmap, directory, fileName)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    /**
     * Сохраняет иконку workout плана с фиксированным соотношением 16:9
     * Обрезает изображение по центру если нужно
     */
    suspend fun saveWorkoutPlanIconCropped(uri: Uri): String? {
        return withContext(Dispatchers.IO) {
            try {
                // Сжимаем изображение для workout плана
                val compressedBitmap = loadAndCompressImage(
                    uri,
                    WORKOUT_MAX_WIDTH,
                    WORKOUT_MAX_HEIGHT
                ) ?: return@withContext null

                // Обрезаем до 16:9 если нужно
                val croppedBitmap = cropToAspectRatio(compressedBitmap, 16f / 9f)

                // Освобождаем оригинальный bitmap если создали новый
                if (croppedBitmap != compressedBitmap) {
                    compressedBitmap.recycle()
                }

                // Сохраняем
                val directory = File(context.filesDir, WORKOUT_IMAGES_DIR)
                val fileName = "${WORKOUT_PLAN_PREFIX}${System.currentTimeMillis()}.jpg"

                saveBitmapToFile(croppedBitmap, directory, fileName)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    /**
     * Обрезает bitmap до нужного соотношения сторон (crop по центру)
     */
    private fun cropToAspectRatio(bitmap: Bitmap, targetAspectRatio: Float): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val currentAspectRatio = width.toFloat() / height.toFloat()

        // Если соотношение уже подходит
        if (abs(currentAspectRatio - targetAspectRatio) < 0.01f) {
            return bitmap
        }

        val newWidth: Int
        val newHeight: Int
        val startX: Int
        val startY: Int

        if (currentAspectRatio > targetAspectRatio) {
            // Изображение слишком широкое - обрезаем по ширине
            newHeight = height
            newWidth = (height * targetAspectRatio).toInt()
            startX = (width - newWidth) / 2
            startY = 0
        } else {
            // Изображение слишком высокое - обрезаем по высоте
            newWidth = width
            newHeight = (width / targetAspectRatio).toInt()
            startX = 0
            startY = (height - newHeight) / 2
        }

        return Bitmap.createBitmap(bitmap, startX, startY, newWidth, newHeight)
    }

    /**
     * Удаляет конкретную иконку workout плана
     */
    suspend fun deleteWorkoutPlanIcon(filePath: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val file = File(filePath)
                file.exists() && file.delete()
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
}