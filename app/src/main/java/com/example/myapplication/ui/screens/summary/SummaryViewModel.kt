package com.example.myapplication.ui.screens.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.ServiceLocator
import com.example.myapplication.data.model.RewardSummary
import com.example.myapplication.data.repository.OceanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SummaryUiState(val summary: RewardSummary? = null)

class SummaryViewModel(
    private val repository: OceanRepository = ServiceLocator.oceanRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SummaryUiState())
    val uiState: StateFlow<SummaryUiState> = _uiState.asStateFlow()

    init { _uiState.value = SummaryUiState(repository.getRewardSummary()) }

    companion object {
        val Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T = SummaryViewModel() as T
        }
    }
}
