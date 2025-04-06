package com.example.gymify.core.util

import com.example.gymify.R

//object ExerciseIconMapper {
//    private val iconMap = mapOf(
//        "push_up" to R.drawable.push_up,
//        "squat" to R.drawable.squat,
//        "deadlift" to R.drawable.deadlift
//    )
//
//    fun getIcon(stringId: String): Int {
//        return iconMap[stringId] ?: R.drawable.ellipse_1 // Иконка по умолчанию
//    }
//}
//
//object ExerciseNameMapper {
//    private val nameMap = mapOf(
//        "push_up" to R.string.push_up
//    )
//
//    fun getName(nameKey: String): Int {
//        return nameMap[nameKey] ?: R.string.heze
//    }
//}
//
//object WorkoutPlanIconMapper {
//    private val iconMap = mapOf(
//        "" to R.drawable.plan_1
//    )
//
//    fun getIcon(iconId: String): Int {
//        return iconMap[iconId] ?: R.drawable.standard_icon
//    }
//}

object MuscleGroupNameMapper {
    private val nameMap = mapOf(
        "muscle_chest" to R.string.muscle_chest,
        "muscle_back" to R.string.muscle_back,
        "muscle_biceps" to R.string.muscle_biceps,
        "muscle_triceps" to R.string.muscle_triceps,
        "muscle_shoulders" to R.string.muscle_shoulders,
        "muscle_legs" to R.string.muscle_legs,
        "muscle_abdominals" to R.string.muscle_abdominals
    )

    fun getName(nameKey: String) : Int {
        return nameMap[nameKey] ?: R.string.no_muscle_group_selected
    }
}