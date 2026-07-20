package com.example.myapplication.ui.screens.analysis

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.model.RewardType
import com.example.myapplication.data.model.SpendingItem
import com.example.myapplication.ui.components.AppTopBar
import com.example.myapplication.ui.components.Chip
import com.example.myapplication.ui.components.ChipStyle
import com.example.myapplication.ui.components.PrimaryButton
import com.example.myapplication.ui.components.StatusBar
import com.example.myapplication.ui.theme.OceanColors

@Composable
fun AnalysisScreen(
    onBack: () -> Unit,
    onOpenSummary: () -> Unit,
    viewModel: AnalysisViewModel = viewModel(factory = AnalysisViewModel.Factory),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(Modifier.fillMaxSize().background(OceanColors.Background)) {
        StatusBar()
        AppTopBar(title = "이번 주 소비 분석", onBack = onBack)

        // 안내 문구 + 요약 칩
        Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 4.dp)) {
            Text(
                "탄소중립포인트 실적과 마이데이터 카드 내역이 연동되어 자동으로 적립됩니다.",
                color = OceanColors.TextSecondary, fontSize = 12.sp
            )
            Row(Modifier.padding(top = 7.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Chip("자동 적립 ${state.autoCount}건", ChipStyle.BlueFilled)
                Chip("미해당 ${state.notEligibleCount}건", ChipStyle.GrayFilled)
            }
        }

        LazyColumn(Modifier.weight(1f)) {
            itemsIndexed(state.items) { index, item ->
                SpendingRow(item)
                if (index < state.items.lastIndex) {
                    Box(Modifier.fillMaxWidth().height(1.dp).background(OceanColors.Border))
                }
            }
        }

        // 하단 요약 바
        Row(
            Modifier.fillMaxWidth().height(88.dp)
                .background(OceanColors.Surface)
                .border(1.dp, OceanColors.Border)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text("이번 주 자동 적립", color = OceanColors.TextSecondary, fontSize = 10.sp)
                Text("물고기 ${state.totalFish}마리", color = OceanColors.TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            PrimaryButton(
                text = "적립 요약 보기",
                onClick = onOpenSummary,
                height = 44,
                fillWidth = false
            )
        }
    }
}

@Composable
private fun SpendingRow(item: SpendingItem) {
    Row(
        Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
            val chipStyle = when (item.type) {
                RewardType.ENV_CERTIFIED -> ChipStyle.DarkFilled
                RewardType.AI_MATCHED -> ChipStyle.InfoOutlined
                RewardType.NOT_ELIGIBLE -> ChipStyle.GrayFilled
            }
            Chip(item.badgeLabel, chipStyle, fontSize = 9)
            Text(
                item.title,
                color = if (item.type == RewardType.NOT_ELIGIBLE) OceanColors.TextSecondary else OceanColors.TextPrimary,
                fontSize = 13.sp, fontWeight = FontWeight.Medium
            )
            Text(item.description, color = OceanColors.TextSecondary, fontSize = 10.sp)
        }
        if (item.points > 0) {
            Text("+${item.points}", color = OceanColors.PointGreen, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        } else {
            Text("적립 없음", color = OceanColors.TextTertiary, fontSize = 10.sp)
        }
    }
}
