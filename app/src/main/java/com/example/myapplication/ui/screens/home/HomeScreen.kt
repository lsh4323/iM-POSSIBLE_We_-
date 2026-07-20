package com.example.myapplication.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.R
import com.example.myapplication.navigation.BottomTab
import com.example.myapplication.ui.components.OceanBottomBar
import com.example.myapplication.ui.home.components.CharacterImage
import com.example.myapplication.ui.home.components.AnimalIntroductionNotification
import com.example.myapplication.ui.home.components.AnimalIntroductionPopup
import com.example.myapplication.ui.home.components.HouseImage
import com.example.myapplication.ui.home.components.LetterPopup
import com.example.myapplication.ui.home.components.MenuButton
import com.example.myapplication.ui.theme.OceanFriendsTheme

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    onNavigateToHouse: () -> Unit,
    onNavigateToMenu: () -> Unit,
    onSelectTab: (BottomTab) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onHouseClick = onNavigateToHouse,
        onMenuClick = onNavigateToMenu,
        onDisableAlarm = viewModel::clearHomeNotification,
        onCharacterClick = viewModel::showLetterPopup,
        onDismissLetterPopup = viewModel::hideLetterPopup,
        onAnimalIntroductionClick =
            viewModel::showAnimalIntroductionPopup,
        onDismissAnimalIntroductionPopup =
            viewModel::hideAnimalIntroductionPopup,
        onSelectTab = onSelectTab
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onHouseClick: () -> Unit,
    onMenuClick: () -> Unit,
    onDisableAlarm: () -> Unit,
    onCharacterClick: () -> Unit,
    onDismissLetterPopup: () -> Unit,
    onAnimalIntroductionClick: () -> Unit,
    onDismissAnimalIntroductionPopup: () -> Unit,
    onSelectTab: (BottomTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            OceanBottomBar(
                selected = BottomTab.HOME,
                onSelect = onSelectTab
            )
        }
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(R.drawable.dandi_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )

            MenuButton(
                isCloseMode =
                    uiState.isAlarmEnabled ||
                            uiState.isAnimalIntroductionVisible,
                onClick = {
                    if (
                        uiState.isAlarmEnabled ||
                        uiState.isAnimalIntroductionVisible
                    ) {
                        onDisableAlarm()
                    } else {
                        onMenuClick()
                    }
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        top = 24.dp,
                        end = 22.dp
                    )
            )

            if (uiState.isAnimalIntroductionVisible) {
                AnimalIntroductionNotification(
                    imageRes = R.drawable.haedi,
                    imageContentDescription = "해디 캐릭터",
                    title = "점박이 물범",
                    description = "점박이 물범 친구가 인사하러 왔어요.",
                    onClick = onAnimalIntroductionClick,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(
                            top = 86.dp,
                            start = 10.dp,
                            end = 10.dp
                        )
                )
            }

            HouseImage(
                imageRes = R.drawable.igloo,
                onClick = onHouseClick,
                contentDescription = "이글루 내부로 이동",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(
                        end = 8.dp,
                        bottom = maxHeight * 0.25f
                    )
                    .width(maxWidth * 0.55f)
            )

            CharacterImage(
                imageRes = if (uiState.isAlarmEnabled) {
                    R.drawable.dandi_letter
                } else {
                    R.drawable.dandi
                },
                contentDescription = "단디 캐릭터",
                enabled = uiState.isAlarmEnabled,
                onClick = onCharacterClick,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(
                        start = maxWidth * 0.03f,
                        bottom = maxHeight * 0.035f
                    )
                    .width(maxWidth * 0.7f)
            )

            if (uiState.isLetterPopupVisible) {
                LetterPopup(
                    onDismiss = onDismissLetterPopup
                )
            }

            if (uiState.isAnimalIntroductionPopupVisible) {
                AnimalIntroductionPopup(
                    imageRes = R.drawable.haedi_real,
                    imageContentDescription = "점박이 물범 실제 사진",
                    title = "점박이 물범",
                    description = SPOTTED_SEAL_DESCRIPTION,
                    onDismiss = onDismissAnimalIntroductionPopup
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=360dp,height=800dp,dpi=440"
)
@Composable
private fun HomeScreenPreview() {
    OceanFriendsTheme {
        HomeScreen(
            uiState = HomeUiState(
                isAnimalIntroductionVisible = true
            ),
            onHouseClick = {},
            onMenuClick = {},
            onDisableAlarm = {},
            onCharacterClick = {},
            onDismissLetterPopup = {},
            onAnimalIntroductionClick = {},
            onDismissAnimalIntroductionPopup = {},
            onSelectTab = {}
        )
    }
}