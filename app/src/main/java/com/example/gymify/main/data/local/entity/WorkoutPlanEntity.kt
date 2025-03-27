package com.example.gymify.main.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gymify.core.domain.model.ExpertiseLevel
import java.time.Instant

@Entity(tableName = "workout_plans")
data class WorkoutPlanEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val workoutPlanNameId: String? = null, // Название для res string
    val workoutPlanName: String? = null, // Название предустановленные пользователем
    val lastUsedDate: Instant? = null, // Когда последний раз юзался
    val iconId: String? = null, // Название для res drawable
    val iconUri: String? = null, // URI
    val expertiseLevel: ExpertiseLevel? = null // Уровень подготовки для плана
)
