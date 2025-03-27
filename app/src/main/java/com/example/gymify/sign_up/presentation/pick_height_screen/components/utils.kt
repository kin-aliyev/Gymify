package com.example.gymify.sign_up.presentation.pick_height_screen.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun calculateFontSizeAndAlpha(
    isScrolling: Boolean,
    distanceFromCenter: Int
): Pair<TextUnit, Float> {
    return if (isScrolling) {
        36.sp to 0.8f
    } else {
        when (distanceFromCenter) {
            0 -> 52.sp to 1f
            1 -> 38.sp to 0.75f
            else -> 28.sp to 0.4f
        }
    }
}
