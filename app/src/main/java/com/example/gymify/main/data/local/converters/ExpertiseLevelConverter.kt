package com.example.gymify.main.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.gymify.core.domain.model.ExpertiseLevel

@ProvidedTypeConverter
class ExpertiseLevelConverter {

    @TypeConverter
    fun fromExpertiseLevel(level: ExpertiseLevel?): Int? {
        return level?.expertiseId
    }

    @TypeConverter
    fun toExpertiseLevel(id: Int?): ExpertiseLevel? {
        return id?.let { ExpertiseLevel.entries.first { it.expertiseId == id } }
    }

}