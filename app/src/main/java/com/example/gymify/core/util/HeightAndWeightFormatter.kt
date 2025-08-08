package com.example.gymify.core.util

import com.example.gymify.core.domain.model.UserHeightUnit
import com.example.gymify.core.domain.model.UserWeightUnit

// Конвертация и форматирование веса в зависимости от единицы измерения
fun formatWeight(weight: String, unit: UserWeightUnit): String {
    val numericWeight = weight.toFloatOrNull() ?: return ""

    val finalWeight = when(unit) {
        UserWeightUnit.KG -> numericWeight
        UserWeightUnit.LBS -> numericWeight * 2.20462f
    }

    return if (finalWeight % 1f == 0f) {
        finalWeight.toInt().toString()
    } else {
        "%.1f".format(finalWeight)
    }
}

// Конвертация и форматирование роста в зависимости от единицы измерения
fun formatHeight(height: String, unit: UserHeightUnit): String {
    val numericHeight = height.toFloatOrNull() ?: return ""

    return when(unit) {
        UserHeightUnit.CM -> {
            if (numericHeight % 1f == 0f) {
                numericHeight.toInt().toString()
            } else {
                "%.1f".format(numericHeight)
            }
        }
        UserHeightUnit.FT -> {
            val totalInches = numericHeight / 2.54f // переводим см в дюймы
            val feet = (totalInches / 12).toInt()   // получаем футы
            val inches = (totalInches % 12).toInt() // остаток - дюймы
            "$feet'$inches\""
        }
    }
}
