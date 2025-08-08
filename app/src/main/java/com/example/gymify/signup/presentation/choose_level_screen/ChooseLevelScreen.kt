package com.example.gymify.signup.presentation.choose_level_screen

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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.R
import com.example.gymify.core.domain.model.ExpertiseLevel
import com.example.gymify.signup.presentation.choose_level_screen.components.LevelButton
import com.example.gymify.core.presentation.components.BackIconButton
import com.example.gymify.signup.presentation.components.ContinueButton
import com.example.gymify.signup.presentation.components.ProgressBar
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun ChooseLevelScreen(
    modifier: Modifier = Modifier,
    expertiseLevel: ExpertiseLevel,
    onAction: (ChooseLevelAction) -> Unit,
    onNavigateToMainModule: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 42.dp)
                .align(Alignment.TopCenter),
        ) {
            // Back Icon |  Progress Bar
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(Modifier.fillMaxWidth()) {
                    BackIconButton(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 12.dp),
                        onBackIconButtonClick = onNavigateBack
                    )

                    ProgressBar(
                        progress = 1f,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }

            Spacer(Modifier.height(90.dp))

            Text(
                text = stringResource(R.string.your_expertise_level),
                color = Color.White,
                fontSize = 32.sp,
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = stringResource(R.string.you_can_change_in_the_settings),
                color = Color(0xFFEBEBEB),
                fontFamily = rubikFontFamily,
                fontSize = 12.sp,
                modifier = Modifier.alpha(0.3f),
                fontWeight = FontWeight.Medium
            )

//            Spacer(Modifier.height(100.dp))

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            LevelButton(
                text = stringResource(R.string.level_beginner),
                isSelected = expertiseLevel == ExpertiseLevel.BEGINNER,
                onLevelClick = {
                    onAction(ChooseLevelAction.onExpertiseLevelClick(ExpertiseLevel.BEGINNER))
                },
            )

            Spacer(Modifier.height(22.dp))

            LevelButton(
                text = stringResource(R.string.level_intermediate),
                isSelected = expertiseLevel == ExpertiseLevel.INTERMEDIATE,
                onLevelClick = {
                    onAction(ChooseLevelAction.onExpertiseLevelClick(ExpertiseLevel.INTERMEDIATE))
                }
            )
            Spacer(Modifier.height(22.dp))

            LevelButton(
                text = stringResource(R.string.level_advanced),
                isSelected = expertiseLevel == ExpertiseLevel.ADVANCED,
                onLevelClick = {
                    onAction(ChooseLevelAction.onExpertiseLevelClick(ExpertiseLevel.ADVANCED))
                }
            )
        }


        ContinueButton(
            text = stringResource(R.string.finish),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 56.dp)
        ) {
            onAction(ChooseLevelAction.SaveExpertiseLevel)
            onNavigateToMainModule()
        }
    }
}

@Preview
@Composable
private fun ChooseLevelScreenPreview() {
    GymifyTheme {
        ChooseLevelScreen(
            onAction = { },
            onNavigateToMainModule = { },
            expertiseLevel = ExpertiseLevel.BEGINNER,
            onNavigateBack = { })
    }
}