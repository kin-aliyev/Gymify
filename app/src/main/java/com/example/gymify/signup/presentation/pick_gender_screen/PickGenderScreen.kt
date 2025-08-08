package com.example.gymify.signup.presentation.pick_gender_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import com.example.gymify.core.domain.model.UserGender
import com.example.gymify.signup.presentation.components.ContinueButton
import com.example.gymify.signup.presentation.components.ProgressBar
import com.example.gymify.signup.presentation.pick_gender_screen.components.GenderPickItem
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PickGenderScreen(
    modifier: Modifier = Modifier,
    userGender: UserGender?,
    onAction: (PickGenderAction) -> Unit,
    onNavigateToHeightScreen: () -> Unit,
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProgressBar(progress = 0.25f)

            Spacer(modifier = Modifier.height(90.dp))

            Text(
                text = stringResource(R.string.gender_question),
                color = Color.White,
                fontSize = 32.sp,
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight.SemiBold
            )

        }

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            maxItemsInEachRow = 2
        ) {
            GenderPickItem(
                text = stringResource(R.string.gender_male), icon = R.drawable.icon_male_symbol,
                modifier = Modifier.padding(end = 24.dp),
                isSelected = userGender == UserGender.MALE,
                onGenderClick = {
                    onAction(PickGenderAction.onGenderClick(UserGender.MALE))
                }
            )
            GenderPickItem(
                text = stringResource(R.string.gender_female),
                icon = R.drawable.icon_female_symbol,
                isSelected = userGender == UserGender.FEMALE,
                onGenderClick = {
                    onAction(PickGenderAction.onGenderClick(UserGender.FEMALE))
                }
            )
            GenderPickItem(
                text = stringResource(R.string.gender_other),
                isSelected = userGender == UserGender.OTHER,
                onGenderClick = {
                    onAction(PickGenderAction.onGenderClick(UserGender.OTHER))
                }
            )
        }

        ContinueButton(
            text = stringResource(R.string.button_continue),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 56.dp)
        ) {
            if (userGender != null) {
                onAction(PickGenderAction.SaveGender)
                onNavigateToHeightScreen()
            }
        }
    }
}

@Preview
@Composable
private fun PickGenderScreenPreview() {
    GymifyTheme {
        PickGenderScreen(onAction = {}, onNavigateToHeightScreen = {}, userGender = null)
    }

}