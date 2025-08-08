package com.example.gymify.signup.presentation.pick_weight_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.R
import com.example.gymify.core.domain.model.UserWeightUnit
import com.example.gymify.core.presentation.components.BackIconButton
import com.example.gymify.signup.presentation.components.ContinueButton
import com.example.gymify.signup.presentation.components.ProgressBar
import com.example.gymify.signup.presentation.components.ToggleButton
import com.example.gymify.signup.presentation.pick_weight_screen.components.WeightPicker
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun PickWeightScreen(
    modifier: Modifier = Modifier,
    userWeightUnit: UserWeightUnit,
    onAction: (PickWeightAction) -> Unit,
    onNavigateToUserLevel: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 42.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(Modifier.fillMaxWidth()) {
                    BackIconButton(
                        modifier = Modifier.padding(start = 12.dp),
                        onBackIconButtonClick = onNavigateBack
                    )

                    ProgressBar(
                        progress = 0.75f,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Spacer(modifier = Modifier.height(90.dp))

            Text(
                text = stringResource(R.string.enter_your_height),
                color = Color.White,
                fontSize = 32.sp,
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(40.dp))

            ToggleButton(
                firstChoice = stringResource(R.string.unit_lbs),
                secondChoice = stringResource(R.string.unit_kg),
                firstOption = UserWeightUnit.LBS,
                secondOption = UserWeightUnit.KG,
                selectedOption = userWeightUnit
            ) { userWeightUnit ->
                onAction(PickWeightAction.onWeightUnitClick(userWeightUnit))
            }

            Spacer(Modifier.height(190.dp))

            WeightPicker(
                onWeightChange = { onAction(PickWeightAction.onWeightChange(it)) },
                isLbs = userWeightUnit.unitId == 0,
                modifier = Modifier.padding(horizontal = 30.dp)
            )

            Spacer(Modifier.height(45.dp))


        }

        ContinueButton(
            text = stringResource(R.string.button_continue),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 56.dp)
        ) {
            onAction(PickWeightAction.SaveWeightUnit)
            onAction(PickWeightAction.SaveWeight)
            onNavigateToUserLevel()
        }
    }

}

@Preview
@Composable
private fun PickWeightScreenPreview() {
    GymifyTheme {
        PickWeightScreen(
            userWeightUnit = UserWeightUnit.KG,
            onAction = { },
            onNavigateToUserLevel = { },
            onNavigateBack = {}
        )
    }

}