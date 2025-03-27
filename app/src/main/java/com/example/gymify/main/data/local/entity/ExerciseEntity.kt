package com.example.gymify.main.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.gymify.main.domain.model.MuscleGroup

@Entity(
    tableName = "exercises",
    indices = [Index(value = ["exerciseNameId"], unique = true)]
)
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val exerciseNameId: String, // Название для БД
    val muscleGroup: MuscleGroup, // К какой мышечной группе относится
    val firstIconId: String, // Название для drawable id
    val secondIconId: String? = null, // Название для второй drawableId если имеется
    val supportsWeight: Boolean // Поддерживает вес или нет
)
