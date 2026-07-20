package com.example.myapplication.ui.screens.analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.ServiceLocator
import com.example.myapplication.data.model.RewardType
import com.example.myapplication.data.model.SpendingItem
import com.example.myapplication.data.repository.OceanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AnalysisUiState(
    val items: List<SpendingItem> = emptyList(),
    val autoCount: Int = 0,        // 자동 적립 건수
    val notEligibleCount: Int = 0, // 미해당 건수
    val totalFish: Int = 0,        // 이번 주 자동 적립 물고기
)

class AnalysisViewModel(
    private val repository: OceanRepository = ServiceLocator.oceanRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnalysisUiState())
    val uiState: StateFlow<AnalysisUiState> = _uiState.asStateFlow()

    init { load() }

    private fun load() {
        val items = repository.getWeeklySpending()
        _uiState.value = AnalysisUiState(
            items = items,
            autoCount = items.count { it.type != RewardType.NOT_ELIGIBLE },
            notEligibleCount = items.count { it.type == RewardType.NOT_ELIGIBLE },
            totalFish = items.sumOf { it.points }
        )
    }

    companion object {
        val Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T = AnalysisViewModel() as T
        }
    }
}
