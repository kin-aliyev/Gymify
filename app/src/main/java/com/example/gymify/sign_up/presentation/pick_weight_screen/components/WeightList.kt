package com.example.gymify.sign_up.presentation.pick_weight_screen.components

import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.sign_up.presentation.pick_height_screen.components.calculateFontSizeAndAlpha
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily
import kotlinx.coroutines.delay
import kotlin.math.abs


// короче смотри пока что все работает хорошо но.
// Я хочу короче сделать animateScroll to item центральному.
// А потом еще чтобы оффсет был такой чтобы этот центральный элемент был тип не слева, а по центру.
// (Я подумал может взять полностью размер lazy row видимого поделить на два
// и отнять размер самого элемента ну его оффсет по width. и таким образом он должен быть по середине)
@Composable
fun WeightList(
    modifier: Modifier = Modifier,
    weightRange: List<Float>,
    onWeightChange: (Float) -> Unit,
    isLbs: Boolean = false,
) {
    val lazyListState = rememberLazyListState(initialFirstVisibleItemIndex = weightRange.size / 5)
    var centerIndex by remember { mutableIntStateOf(weightRange.size / 2) }


    LaunchedEffect(lazyListState) {
        while (true) {
            delay(500) // Проверяем каждые 500 мс
            val layoutInfo = lazyListState.layoutInfo
            val viewportCenter = layoutInfo.viewportStartOffset + layoutInfo.viewportSize.width / 2
            val visibleItems = layoutInfo.visibleItemsInfo

            if (visibleItems.isNotEmpty()) {
                val centerItem = visibleItems.minByOrNull { item ->
                    val itemCenter = item.offset + item.size / 2
                    abs(itemCenter - viewportCenter)
                }

                centerItem?.let { item ->
                    if (centerIndex != item.index) {
                        centerIndex = item.index
                        onWeightChange(weightRange[item.index])
                    }
                }
            }
        }
    }


//    LaunchedEffect(lazyListState) {
//        snapshotFlow { lazyListState.isScrollInProgress }
//            .collect { isScrolling ->
//                if (!isScrolling) {
//                    val layoutInfo = lazyListState.layoutInfo
//                    val viewportCenter =
//                        layoutInfo.viewportStartOffset + layoutInfo.viewportSize.width / 2
//                    val visibleItems = layoutInfo.visibleItemsInfo
//
//                    if (visibleItems.isNotEmpty()) {
//                        val centerItem = visibleItems.minByOrNull { item ->
//                            val itemCenter = item.offset + item.size / 2
//                            abs(itemCenter - viewportCenter)
//                        }
//
//                        centerItem?.let { item ->
//                            // Обновляем индекс центрального элемента
//                            if (centerIndex != item.index) {
//                                centerIndex = item.index
//                                onWeightChange(weightRange[item.index])
//                            }
//
//                            // Центрируем элемент, если он не на месте
//                            val itemCenterOffset = item.offset + item.size / 2
//                            val distanceToCenter = itemCenterOffset - viewportCenter
//
//                            if (abs(distanceToCenter) > 1f) { // Минимизируем вызовы анимации
//                                lazyListState.animateScrollBy(distanceToCenter.toFloat())
//                            }
//
//                            Log.d("LazyListDebug", "Viewport Center: $viewportCenter")
//                            Log.d("LazyListDebug", "Visible Items: $visibleItems")
//                            Log.d("LazyListDebug", "Selected Center Item: ${centerItem?.index}")
//                        }
//                    }
//
//                }
//            }
//    }


    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.isScrollInProgress }
            .collect { isScrolling ->
                if (!isScrolling) {
                    val layoutInfo = lazyListState.layoutInfo
                    val viewportCenter =
                        layoutInfo.viewportStartOffset + layoutInfo.viewportSize.width / 2
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
                                onWeightChange(weightRange[item.index])
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

    LazyRow(
        state = lazyListState,
        horizontalArrangement = Arrangement.spacedBy(30.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        items(weightRange.size, key = { index -> weightRange[index].toInt() }) { index ->
            val isScrolling = lazyListState.isScrollInProgress
            val distanceFromCenter = abs(index - centerIndex)

            // Проверяем, является ли элемент одним из первых двух или последних двух
            val itemAlpha = if (index < 2 || index >= weightRange.size - 2) {
                0f // Полностью прозрачный
            } else {
                // Вычисляем прозрачность для остальных элементов
                calculateFontSizeAndAlpha(isScrolling, distanceFromCenter).second
            }

            val itemFontSize = calculateFontSizeAndAlpha(isScrolling, distanceFromCenter).first

            WeightListItem(
                weight = weightRange[index],
                isLbs = isLbs,
                fontSize = itemFontSize,
                alpha = itemAlpha
            )
        }
    }

}

@Composable
fun WeightListItem(
    modifier: Modifier = Modifier,
    weight: Float,
    isLbs: Boolean = false,
    fontSize: TextUnit = 52.sp,
    alpha: Float = 1f,
) {
    val formattedWeight = if (isLbs) {
        val lbs = (weight * 2.2f).toInt()
        "$lbs"
    } else weight.toInt().toString()

    Text(
        text = formattedWeight,
        fontSize = fontSize,
        fontFamily = rubikFontFamily,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
        color = Color(0xFFEBEBEB),
        modifier = modifier
            .alpha(alpha)
    )
}

@Preview
@Composable
private fun WeightListPreview() {
    GymifyTheme {
        WeightList(
            weightRange = (40..200).map { it.toFloat() },
            onWeightChange = { },
            isLbs = false
        )
    }
}

//@Preview
//@Composable
//private fun WeightListItemsPreview(modifier: Modifier = Modifier) {
//    GymifyTheme {
//        Row(
//            modifier = modifier
//                .fillMaxWidth()
//                .background(MaterialTheme.colorScheme.surface),
//            horizontalArrangement = Arrangement.spacedBy(20.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            WeightListItem(weight = 50f, fontSize = 28.sp, alpha = 0.4f)
//            WeightListItem(weight = 55f, fontSize = 38.sp, alpha = 0.75f)
//            WeightListItem(weight = 60f, fontSize = 52.sp, alpha = 1f)
//            WeightListItem(weight = 65f, fontSize = 38.sp, alpha = 0.75f)
//            WeightListItem(weight = 70f, fontSize = 28.sp, alpha = 0.4f)
//        }
//    }
//}