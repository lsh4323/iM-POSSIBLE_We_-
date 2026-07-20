package com.example.myapplication.ui.screens.together

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class TogetherUiState(
    val campaignName: String = "OO 갯벌복원사업",
    val basis: String = "갯벌복원사업 지침 제5조",
    val progress: Float = 0.68f,
    val raised: String = "340만원",
    val goal: String = "500만원",
    val participants: Int = 1284,
    val myFish: Int = 200,
    val myWon: Int = 2000,
)

class TogetherViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TogetherUiState())
    val uiState: StateFlow<TogetherUiState> = _uiState.asStateFlow()

    companion object {
        val Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T = TogetherViewModel() as T
        }
    }
}
