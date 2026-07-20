package com.example.myapplication.ui.screens.donate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.ServiceLocator
import com.example.myapplication.data.model.DonationEffect
import com.example.myapplication.data.model.DonationTarget
import com.example.myapplication.data.repository.OceanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class DonateUiState(
    val heldFish: Int = 0,
    val targets: List<DonationTarget> = emptyList(),
    val selectedTargetId: String? = null,
    val amountFish: Int = 0,      // 보낼 물고기 수
    val effects: List<DonationEffect> = emptyList(),
) {
    /** 물고기 → 원 (1마리 = 10원) */
    val heldWon: Int get() = heldFish * FISH_TO_WON
    val amountWon: Int get() = amountFish * FISH_TO_WON
    val selectedTarget: DonationTarget? get() = targets.firstOrNull { it.id == selectedTargetId }

    companion object { const val FISH_TO_WON = 10 }
}

class DonateViewModel(
    private val repository: OceanRepository = ServiceLocator.oceanRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DonateUiState())
    val uiState: StateFlow<DonateUiState> = _uiState.asStateFlow()

    init {
        val summary = repository.getOceanSummary()
        val targets = repository.getDonationTargets()
        _uiState.value = DonateUiState(
            heldFish = summary.fishCount,
            targets = targets,
            selectedTargetId = targets.firstOrNull()?.id,
            amountFish = 200,
            effects = repository.getDonationEffects()
        )
    }

    fun selectTarget(id: String) {
        _uiState.value = _uiState.value.copy(selectedTargetId = id)
    }

    fun setAmount(fish: Int) {
        val clamped = fish.coerceIn(0, _uiState.value.heldFish)
        _uiState.value = _uiState.value.copy(amountFish = clamped)
    }

    companion object {
        val Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T = DonateViewModel() as T
        }
    }
}
