package com.example.myapplication.ui.screens.home

data class HomeUiState(
    val title: String = "홈",
    val message: String = "",
    val name: String = "",
    val isAlarmEnabled: Boolean = false,
    val isLetterPopupVisible: Boolean = false,
    val isAnimalIntroductionVisible: Boolean = false,
    val isAnimalIntroductionPopupVisible: Boolean = false
)