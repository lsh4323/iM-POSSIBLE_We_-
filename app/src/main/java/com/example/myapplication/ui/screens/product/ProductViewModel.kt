package com.example.myapplication.ui.screens.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.ServiceLocator
import com.example.myapplication.data.model.RateComponent
import com.example.myapplication.data.repository.OceanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ProductUiState(
    val productName: String = "iM 바다친구 적금",
    val grade: Int = 3,
    val totalRate: String = "3.30",
    val condition: String = "12개월 · 월 50만원 한도",
    val components: List<RateComponent> = emptyList(),
)

class ProductViewModel(
    private val repository: OceanRepository = ServiceLocator.oceanRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = ProductUiState(
            grade = repository.getOceanSummary().grade,
            components = repository.getProductRateComponents()
        )
    }

    companion object {
        val Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T = ProductViewModel() as T
        }
    }
}
