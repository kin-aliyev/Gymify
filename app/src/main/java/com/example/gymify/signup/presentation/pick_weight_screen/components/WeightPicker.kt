package com.example.gymify.signup.presentation.pick_weight_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.ui.theme.GymifyTheme

@Composable
fun WeightPicker(
    modifier: Modifier = Modifier,
    onWeightChange: (Float) -> Unit,
    isLbs: Boolean = false,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        WeightList(
            weightRange = (35..200).map { it.toFloat() },
            onWeightChange = onWeightChange,
            isLbs = isLbs
        )
        Spacer(Modifier.height(10.dp))

        WeightMeasureRuler()

        Spacer(Modifier.height(12.dp))

        WeightTriangle()
    }
}

@Preview
@Composable
private fun WeightPickerPreview() {
    GymifyTheme {
        WeightPicker(onWeightChange = { })
    }
}