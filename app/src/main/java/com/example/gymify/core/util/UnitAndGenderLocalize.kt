package com.example.gymify.core.util

import android.content.Context
import com.example.gymify.R
import com.example.gymify.core.domain.model.UserHeightUnit
import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.sign_up.domain.model.UserGender

// Получение локализованного названия гендера
fun UserGender.getLocalizedName(context: Context): String {
    return when(this) {
        UserGender.MALE -> context.getString(R.string.gender_male)
        UserGender.FEMALE -> context.getString(R.string.gender_female)
        UserGender.OTHER -> context.getString(R.string.gender_other)
    }
}

// Получение локализованного названия единицы веса
fun UserWeightUnit.getLocalizedName(context: Context): String {
    return when(this) {
        UserWeightUnit.KG -> context.getString(R.string.unit_kg)
        UserWeightUnit.LBS -> context.getString(R.string.unit_lbs)
    }
}

// Получение локализованного названия единицы высоты
fun UserHeightUnit.getLocalizedName(context: Context): String {
    return when(this) {
        UserHeightUnit.CM -> context.getString(R.string.unit_cm)
        UserHeightUnit.FT -> context.getString(R.string.unit_ft)
    }
}