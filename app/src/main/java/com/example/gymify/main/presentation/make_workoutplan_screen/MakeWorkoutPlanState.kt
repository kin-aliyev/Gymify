package com.example.gymify.main.presentation.make_workoutplan_screen

import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.main.domain.model.Exercise
import com.example.gymify.main.domain.model.MuscleGroup
import com.example.gymify.main.domain.model.WorkoutExerciseWithExerciseInfo

data class MakeWorkoutPlanState(

    val planName: String = "",
    // Чтобы workout exercise мог сохраниться в list of added exercise до того как создать план
    val workoutPlanId: Int = 0,

    val exerciseNameKey: String? = null, // Текстовое значение(ключ) выбранного упражнения
    val selectedMuscleGroup: MuscleGroup? = null, // Выбранная мышечная группа (может быть null, если еще не выбрана)
    // val muscleGroupName: String = "", // Не понял еще нужен или нет скорее всего нет ибо у muscle group есть свой key id
    val availableExercises: List<Exercise> = emptyList(), // Список упражнений, доступных для выбранной группы

    // Значения, введённые пользователем для текущего упражнения (если используется отдельно)
    val numberOfSets: String = "",
    val numberOfReps: String = "",
    val weight: String = "",

    // Выбранные упражнения с параметрами. Ключ — stringId (String), значение – агрегированные данные (напр., сеты, повторения, вес)
    val selectedExercises: Map<String, WorkoutExerciseWithExerciseInfo> = emptyMap(),

    val userWeightUnit: UserWeightUnit = UserWeightUnit.KG,  // Универсальный тип единицы измерения для веса (например, KG или LBS)

    // Отслежвание какое упражнений в списке сейчас редактируется
    val editingExerciseId: String? = null,


)

