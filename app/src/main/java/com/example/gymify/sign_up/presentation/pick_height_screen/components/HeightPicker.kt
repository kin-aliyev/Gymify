package com.example.gymify.sign_up.presentation.pick_height_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.ui.theme.GymifyTheme

@Composable
fun HeightPicker(
    modifier: Modifier = Modifier,
    onHeightChange: (Float) -> Unit,
    isFeet: Boolean = false,
) {
    GymifyTheme {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HeightList(
                heightRange = (150..200).map { it.toFloat() },
                onHeightChange = onHeightChange,
                isFeet = isFeet,
            )

            Row {
                HeightMeasureRuler()
                Spacer(Modifier.width(2.dp))
                HeightTriangle()
            }

        }
    }
}

@Preview
@Composable
private fun HeightPickerPreview() {
    GymifyTheme {
        HeightPicker(onHeightChange = { })
    }
}