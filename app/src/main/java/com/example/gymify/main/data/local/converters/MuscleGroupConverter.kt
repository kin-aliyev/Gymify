package com.example.gymify.main.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.gymify.main.domain.model.MuscleGroup

@ProvidedTypeConverter
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