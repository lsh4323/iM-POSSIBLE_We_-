package com.example.myapplication.ui.screens.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun enableAlarm() {
        _uiState.update {
            it.copy(
                isAlarmEnabled = true,
                isLetterPopupVisible = false,
                isAnimalIntroductionVisible = false,
                isAnimalIntroductionPopupVisible = false
            )
        }
    }

    fun clearHomeNotification() {
        _uiState.update {
            it.copy(
                isAlarmEnabled = false,
                isLetterPopupVisible = false,
                isAnimalIntroductionVisible = false,
                isAnimalIntroductionPopupVisible = false
            )
        }
    }

    fun showLetterPopup() {
        if (_uiState.value.isAlarmEnabled) {
            _uiState.update {
                it.copy(isLetterPopupVisible = true)
            }
        }
    }

    fun hideLetterPopup() {
        _uiState.update {
            it.copy(isLetterPopupVisible = false)
        }
    }

    fun showAnimalIntroduction() {
        _uiState.update {
            it.copy(
                isAlarmEnabled = false,
                isLetterPopupVisible = false,
                isAnimalIntroductionVisible = true,
                isAnimalIntroductionPopupVisible = false
            )
        }
    }

    fun hideAnimalIntroduction() {
        _uiState.update {
            it.copy(
                isAnimalIntroductionVisible = false,
                isAnimalIntroductionPopupVisible = false
            )
        }
    }

    fun showAnimalIntroductionPopup() {
        if (_uiState.value.isAnimalIntroductionVisible) {
            _uiState.update {
                it.copy(isAnimalIntroductionPopupVisible = true)
            }
        }
    }

    fun hideAnimalIntroductionPopup() {
        _uiState.update {
            it.copy(isAnimalIntroductionPopupVisible = false)
        }
    }
}