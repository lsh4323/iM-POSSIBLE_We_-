package com.example.myapplication.ui.screens.grade

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.ServiceLocator
import com.example.myapplication.data.model.GradeRate
import com.example.myapplication.data.repository.OceanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class GradeUiState(
    val currentGrade: Int = 3,
    val recentFish: Int = 1760,
    val fishToNextGrade: Int = 240,
    val progress: Float = 0.6f,
    val rates: List<GradeRate> = emptyList(),
)

class GradeViewModel(
    private val repository: OceanRepository = ServiceLocator.oceanRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GradeUiState())
    val uiState: StateFlow<GradeUiState> = _uiState.asStateFlow()

    init {
        val summary = repository.getRewardSummary()
        _uiState.value = GradeUiState(
            currentGrade = summary.grade,
            recentFish = 1760,
            fishToNextGrade = summary.fishToNextGrade,
            progress = summary.gradeProgress,
            rates = repository.getGradeRates()
        )
    }

    companion object {
        val Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T = GradeViewModel() as T
        }
    }
}
