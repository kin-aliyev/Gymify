package com.example.gymify.signup.presentation.pick_height_screen.components

import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily
import kotlinx.coroutines.delay
import kotlin.math.abs

@Composable
fun HeightList(
    modifier: Modifier = Modifier,
    heightRange: List<Float>,
    onHeightChange: (Float) -> Unit,
    isFeet: Boolean = false,
) {
    val lazyListState = rememberLazyListState(initialFirstVisibleItemIndex = heightRange.size / 2)
    var centerIndex by remember { mutableIntStateOf(heightRange.size / 2) }

    LaunchedEffect(lazyListState) {
        while (true) {
            delay(500) // Проверяем каждые 500 мс
            val layoutInfo = lazyListState.layoutInfo
            val viewportCenter = layoutInfo.viewportStartOffset + layoutInfo.viewportSize.height / 2
            val visibleItems = layoutInfo.visibleItemsInfo

            if (visibleItems.isNotEmpty()) {
                val centerItem = visibleItems.minByOrNull { item ->
                    val itemCenter = item.offset + item.size / 2
                    abs(itemCenter - viewportCenter)
                }

                centerItem?.let { item ->
                    if (centerIndex != item.index) {
                        centerIndex = item.index
                        onHeightChange(heightRange[item.index])
                    }
                }
            }
        }
    }
    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.isScrollInProgress }
            .collect { isScrolling ->
                if (!isScrolling) {
                    val layoutInfo = lazyListState.layoutInfo
                    val viewportCenter =
                        layoutInfo.viewportStartOffset + layoutInfo.viewportSize.height / 2
                    val visibleItems = layoutInfo.visibleItemsInfo

                    if (visibleItems.isNotEmpty()) {
                        val centerItem = visibleItems.minByOrNull { item ->
                            val itemCenter = item.offset + item.size / 2
                            abs(itemCenter - viewportCenter)
                        }

                        centerItem?.let { item ->
                            // Обновляем индекс и вызываем коллбэк
                            if (centerIndex != item.index) {
                                centerIndex = item.index
                                onHeightChange(heightRange[item.index])
                            }

                            // Проверяем, находится ли элемент уже в центре
                            val itemCenterOffset = item.offset + item.size / 2
                            val distanceToCenter = itemCenterOffset - viewportCenter

                            if (abs(distanceToCenter) > 1f) {
                                // Анимируем к центру
                                lazyListState.animateScrollBy(distanceToCenter.toFloat())
                            }
                        }
                    }
                }
            }
    }

    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(31.dp),
        modifier = modifier
            .height(340.dp)
            .width(130.dp)
            .padding(vertical = 2.dp)
    ) {
        items(heightRange.size, key = { index -> heightRange[index].toInt() }) { index ->
            val distanceFromCenter = abs(index - centerIndex)
            val isScrolling = lazyListState.isScrollInProgress

            val itemAlpha = if (index < 2 || index >= heightRange.size - 2) {
                0f
            } else {
                calculateFontSizeAndAlpha(isScrolling, distanceFromCenter).second
            }

            val itemFontSize = calculateFontSizeAndAlpha(isScrolling, distanceFromCenter).first

            HeightListItem(
                height = heightRange[index],
                fontSize = itemFontSize,
                alpha = itemAlpha,
                isFeet = isFeet
            )
        }
    }
}


@Composable
fun HeightListItem(
    modifier: Modifier = Modifier,
    height: Float,
    fontSize: TextUnit = 42.sp,
    alpha: Float = 1f,
    isFeet: Boolean = false,
) {
    val formattedHeight = remember(height, isFeet) {
        if (isFeet) {
            val totalInches = (height / 2.54f).toInt()
            val feet = totalInches / 12
            val inches = totalInches % 12
            "$feet'$inches\""
        } else {
            height.toInt().toString()
        }
    }

    // Text size 44.sp, 36,sp, 28,sp
    // Alpha 100, 75, 40
    Text(
        text = formattedHeight,
        fontSize = fontSize,
        color = Color(0xFFEBEBEB),
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
        modifier = modifier
            .alpha(alpha)
            .width(130.dp)
    )
}

private fun findClosestItem(
    lazyListState: LazyListState,
): LazyListItemInfo? {
    val visibleItems = lazyListState.layoutInfo.visibleItemsInfo
    val viewportCenter =
        lazyListState.layoutInfo.viewportStartOffset + lazyListState.layoutInfo.viewportSize.height / 2

    return visibleItems.minByOrNull { item ->
        val itemCenter = item.offset + item.size / 2
        abs(itemCenter - viewportCenter)
    }
}

@Preview
@Composable
private fun HeightListPreview() {
    GymifyTheme {
        HeightList(
            heightRange = (150..200).map { it.toFloat() },
            isFeet = true,
            onHeightChange = {})
    }

}

//@Preview(showBackground = true)
//@Composable
//fun PreviewHeightListItem(modifier: Modifier = Modifier) {
//    GymifyTheme {
//        Column(
//            verticalArrangement = Arrangement.spacedBy(12.dp), modifier = modifier
//                .fillMaxSize()
//                .background(color = MaterialTheme.colorScheme.surface)
//        ) {
//            HeightListItem(height = 155f, fontSize = 26.sp, alpha = 0.4f)
//            HeightListItem(height = 160f, fontSize = 36.sp, alpha = 0.75f)
//            HeightListItem(height = 165f, fontSize = 48.sp, alpha = 1f)
//            HeightListItem(height = 170f, fontSize = 36.sp, alpha = 0.75f)
//            HeightListItem(height = 175f, fontSize = 26.sp, alpha = 0.4f)
//        }
//    }
//}