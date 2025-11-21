package com.example.gymify.settings.presentation.helpandsupport

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymify.R
import com.example.gymify.core.presentation.components.BackTopBar
import com.example.gymify.core.presentation.components.BottomNavigationBar
import com.example.gymify.core.presentation.navigation.NavigationDestination
import com.example.gymify.settings.navigation.HelpAndSupport
import com.example.gymify.settings.presentation.helpandsupport.components.HelpAndSupportInfoTab
import com.example.gymify.settings.util.openInstagramProfile
import com.example.gymify.settings.util.openTelegramProfile
import com.example.gymify.ui.theme.GymifyTheme
import com.example.gymify.ui.theme.rubikFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpAndSupportScreen(
    modifier: Modifier = Modifier,
    selectedDestination: NavigationDestination,
    onNavigate: (NavigationDestination) -> Unit,
    onAction: (HelpAndSupportAction) -> Unit,
) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 12.dp),
        topBar = {
            BackTopBar(
                title = "Help & Support",
                onBackIconClick = { onAction(HelpAndSupportAction.NavigateBack) },
                scrollBehavior = scrollBehavior,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        },
        bottomBar = {
            BottomNavigationBar(
                onNavigate = { onNavigate(it) },
                selectedDestination = selectedDestination,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp)
            )
        }
    ) { paddingValues ->
        val combinedPadding = PaddingValues(
            start = paddingValues.calculateStartPadding(LayoutDirection.Ltr) + 12.dp,
            top = paddingValues.calculateTopPadding(),
            end = paddingValues.calculateEndPadding(LayoutDirection.Ltr) + 12.dp,
            bottom = 4.dp
        )
        LazyColumn(
            contentPadding = combinedPadding,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding() + 12.dp)
        ) {
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(R.drawable.form_ellipse_2),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .size(180.dp)
                    )

                    Icon(
                        painter = painterResource(R.drawable.icon_round_handshake),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(120.dp)
                    )
                }

            }

            item {
                Spacer(Modifier.height(4.dp))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Support a safe\nand open project.",
                        textAlign = TextAlign.Center,
                        fontFamily = rubikFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFFEBEBEB)
                    )
                }

            }

            item {
                Spacer(Modifier.height(16.dp))
                HelpAndSupportInfoTab(
                    tabIcon = R.drawable.icon_rounded_globe_location,
                    tabValue = "Azerbaijan, Baku."
                )
            }

            item {
                Spacer(Modifier.height(12.dp))
                HelpAndSupportInfoTab(
                    tabIcon = R.drawable.icon_instagram,
                    tabValue = "kin.d1darov",
                    isForSocialMedia = true,
                    onTabClick = { openInstagramProfile(context) }
                )
            }

            item {
                Spacer(Modifier.height(12.dp))
                HelpAndSupportInfoTab(
                    tabIcon = R.drawable.icon_telegram_logo,
                    tabValue = "kin_aliyev",
                    isForSocialMedia = true,
                    onTabClick = { openTelegramProfile(context) }
                )
            }

            item {
                Spacer(Modifier.height(20.dp))
                HorizontalDivider(thickness = 3.dp, color = Color(0xFF3B4051))
                Spacer(Modifier.height(22.dp))
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.image_porsche),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center),
                            contentScale = ContentScale.Fit
                        )

                        Text(
                            text = "Dream\nbig.\nDrive\nfast.",
                            style = TextStyle(
                                fontSize = 38.sp,
                                lineHeight = 38.sp,
                                fontWeight = FontWeight.ExtraBold,
                                brush = Brush.sweepGradient(colors = listOf(
                                    MaterialTheme.colorScheme.primary,
                                    Color(0xFF617BB6)
                                ))
                            ),
                            fontFamily = rubikFontFamily,

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                                .align(Alignment.CenterStart)
                        )
                    }
                }

            }

            item {
                Spacer(Modifier.height(16.dp))
                HelpAndSupportInfoTab(
                    tabIcon = R.drawable.image_birbank_logo,
                    tabValue = "4169 7388 7620 7378",
                    isForBank = true
                )

            }

            item {
                Spacer(Modifier.height(10.dp))
                HelpAndSupportInfoTab(
                    tabIcon = R.drawable.image_unibank_logo,
                    tabValue = "4098 0944 2263 0879",
                    isForBank = true
                )

            }

            item {
                Spacer(Modifier.height(10.dp))
                HelpAndSupportInfoTab(
                    tabIcon = R.drawable.image_leobank_logo,
                    tabValue = "4098 5844 9137 9606",
                    isForBank = true
                )
            }
        }


    }
}

@Preview(showSystemUi = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HelpAndSupportScreenPreview() {
    GymifyTheme {
        HelpAndSupportScreen(
            onAction = { },
            onNavigate = { },
            selectedDestination = HelpAndSupport
        )
    }
}