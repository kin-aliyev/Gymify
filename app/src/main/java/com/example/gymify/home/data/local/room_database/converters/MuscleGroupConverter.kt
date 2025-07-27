package com.example.gymify.home.data.local.room_database.converters

import androidx.room.TypeConverter
import com.example.gymify.home.domain.model.MuscleGroup

class MuscleGroupConverter {

    @TypeConverter
    fun fromMuscleGroup(muscleGroup: MuscleGroup): String {
        return muscleGroup.name
    }

    @TypeConverter
    fun toMuscleGroup(value: String): MuscleGroup {
        return try {
            MuscleGroup.valueOf(value)
        } catch (e: IllegalArgumentException) {
            MuscleGroup.CHEST // Значение по умолчанию
        }
    }
}