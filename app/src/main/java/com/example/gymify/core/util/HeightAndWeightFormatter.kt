package com.example.gymify.core.util

import com.example.gymify.core.domain.model.UserHeightUnit
import com.example.gymify.core.domain.model.UserWeightUnit

// Конвертация и форматирование веса в зависимости от единицы измерения
fun formatWeight(weight: String, unit: UserWeightUnit): String {
    val numericWeight = weight.toFloatOrNull() ?: 0f

    return when(unit) {
        UserWeightUnit.KG -> numericWeight.toInt().toString()
        UserWeightUnit.LBS -> (numericWeight * 2.20462f).toInt().toString()
    }
}

// Конвертация и форматирование роста в зависимости от единицы измерения
fun formatHeight(height: String, unit: UserHeightUnit): String {
    val numericHeight = height.toFloatOrNull() ?: 0f

    return when(unit) {
        UserHeightUnit.CM -> numericHeight.toString()
        UserHeightUnit.FT -> {
            val feet = (numericHeight / 30.48f).toInt()
            val inches = ((numericHeight / 2.54f) % 12).toInt()
            "$feet'$inches\""
        }
    }
}
