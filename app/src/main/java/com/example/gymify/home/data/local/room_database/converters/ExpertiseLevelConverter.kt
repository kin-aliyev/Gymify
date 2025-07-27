package com.example.gymify.home.data.local.room_database.converters

import androidx.room.TypeConverter
import com.example.gymify.core.domain.model.ExpertiseLevel

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