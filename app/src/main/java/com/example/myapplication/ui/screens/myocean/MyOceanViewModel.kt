package com.example.myapplication.ui.screens.myocean

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.ServiceLocator
import com.example.myapplication.data.model.OceanSummary
import com.example.myapplication.data.repository.OceanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MyOceanUiState(
    val isLoading: Boolean = true,
    val summary: OceanSummary? = null,
)

class MyOceanViewModel(
    private val repository: OceanRepository = ServiceLocator.oceanRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyOceanUiState())
    val uiState: StateFlow<MyOceanUiState> = _uiState.asStateFlow()

    init { load() }

    private fun load() {
        _uiState.value = MyOceanUiState(isLoading = false, summary = repository.getOceanSummary())
    }

    companion object {
        val Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T = MyOceanViewModel() as T
        }
    }
}
