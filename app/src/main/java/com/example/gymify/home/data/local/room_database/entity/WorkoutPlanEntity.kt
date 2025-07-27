package com.example.gymify.home.data.local.room_database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gymify.core.domain.model.ExpertiseLevel

@Entity(tableName = "workout_plans")
data class WorkoutPlanEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val workoutPlanNameId: String? = null, // Название для res string
    val workoutPlanName: String? = null, // Название предустановленные пользователем
    val lastUsedDate: Long? = null, // Когда последний раз юзался
    val iconId: String? = null, // Название для res drawable
    val iconUri: String? = null, // URI
    val expertiseLevel: ExpertiseLevel? = null // Уровень подготовки для плана
)
