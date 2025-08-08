package com.example.gymify.signup.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymify.R
import com.example.gymify.core.domain.model.UserHeightUnit
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@Composable
fun <T> ToggleButton(
    modifier: Modifier = Modifier,
    firstChoice: String,
    secondChoice: String,
    firstOption: T,
    secondOption: T,
    selectedOption: T,
    onChoiceClick: (T) -> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(24.dp)
            ),
    ) {
        ToggleButtonItem(
            text = firstChoice,
            isSelected = selectedOption == firstOption
        ) {
            onChoiceClick(firstOption)
        }

        ToggleButtonItem(
            text = secondChoice,
            isSelected = selectedOption == secondOption
        ) {
            onChoiceClick(secondOption)
        }
    }
}


@Composable
fun ToggleButtonItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    )
    val horizontalPaddingValue by animateDpAsState(
        targetValue = if (isSelected) 17.dp else 12.dp
    )


    Text(
        text = text,
        color = MaterialTheme.colorScheme.background,
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .background(backgroundColor, shape = RoundedCornerShape(24.dp))
            .padding(horizontal = horizontalPaddingValue, vertical = 3.dp)
            .clickable { onClick() }
            .animateContentSize()
            .width(30.dp)
    )
}

@Preview
@Composable
private fun ToggleButtonPreview() {
    var selectedOption by remember { mutableStateOf(UserHeightUnit.CM) }
    GymifyTheme {
        ToggleButton(
            firstChoice = stringResource(R.string.unit_ft),
            secondChoice = stringResource(R.string.unit_cm),
            selectedOption = UserHeightUnit.CM,
            onChoiceClick = { selectedOption = it },
            firstOption = UserHeightUnit.FT,
            secondOption = UserHeightUnit.CM
        )
    }
}