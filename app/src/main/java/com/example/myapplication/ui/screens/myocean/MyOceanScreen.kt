package com.example.myapplication.ui.screens.myocean

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.navigation.BottomTab
import com.example.myapplication.data.model.OceanSummary
import com.example.myapplication.ui.components.OceanBottomBar
import com.example.myapplication.ui.components.PrimaryButton
import com.example.myapplication.ui.components.StatusBar
import com.example.myapplication.ui.theme.OceanColors

@Composable
fun MyOceanScreen(
    onOpenAnalysis: () -> Unit,
    onOpenGrade: () -> Unit,
    onSelectTab: (BottomTab) -> Unit,
    viewModel: MyOceanViewModel = viewModel(factory = MyOceanViewModel.Factory),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val summary = state.summary ?: return

    Column(Modifier.fillMaxSize().background(OceanColors.Background)) {
        StatusBar()
        // Header
        Row(
            Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("바다친구들", color = OceanColors.TextPrimary, fontSize = 19.sp, fontWeight = FontWeight.Bold)
            Box(Modifier.size(28.dp).clip(RoundedCornerShape(50)).background(OceanColors.ChipBlueBg))
        }
        SummaryCard(summary, onOpenGrade)
        OceanScene(summary, Modifier.weight(1f))
        // CTA
        Box(Modifier.fillMaxWidth().padding(20.dp)) {
            PrimaryButton("이번 주 인정된 소비 ${summary.recognizedCount}건 보기", onOpenAnalysis)
        }
        OceanBottomBar(selected = BottomTab.MY_OCEAN, onSelect = onSelectTab)
    }
}

@Composable
private fun SummaryCard(summary: OceanSummary, onOpenGrade: () -> Unit) {
    Box(Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 12.dp).padding(horizontal = 20.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(OceanColors.Surface)
                .border(1.dp, OceanColors.Border, RoundedCornerShape(12.dp))
                .padding(horizontal = 18.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SummaryStat("내 물고기", "%,d".format(summary.fishCount), Alignment.Start)
            Box(Modifier.width(1.dp).height(36.dp).background(OceanColors.Border))
            SummaryStat(
                "바다지킴이 등급", "${summary.grade}등급", Alignment.End,
                modifier = Modifier.clickable { onOpenGrade() }
            )
        }
    }
}

@Composable
private fun SummaryStat(
    label: String,
    value: String,
    align: Alignment.Horizontal,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, horizontalAlignment = align, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(label, color = OceanColors.TextSecondary, fontSize = 11.sp)
        Text(value, color = OceanColors.TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)
    }
}

/** 바다 그라디언트 배경 + 펫/슬롯 플레이스홀더 */
@Composable
private fun OceanScene(summary: OceanSummary, modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .background(Brush.verticalGradient(listOf(OceanColors.OceanTop, OceanColors.OceanBottom)))
    ) {
        Text(
            "내 바다",
            modifier = Modifier.padding(start = 20.dp, top = 16.dp),
            color = OceanColors.OceanText, fontSize = 13.sp, fontWeight = FontWeight.Medium
        )
        Column(
            Modifier.fillMaxSize().padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PetPlaceholder()
            Text(
                "${summary.petSpecies} · Lv.${summary.petLevel}",
                color = OceanColors.TextSecondary, fontSize = 11.sp, fontWeight = FontWeight.Medium
            )
        }
        // 캐릭터 슬롯 (좌/우)
        CharacterSlot("단디", Modifier.align(Alignment.CenterStart).padding(start = 38.dp, top = 40.dp))
        CharacterSlot("똑띠", Modifier.align(Alignment.CenterEnd).padding(end = 38.dp, top = 40.dp))
        Text(
            "※ 캐릭터·아이템 = 플레이스홀더 (공식 에셋 확보 후 교체)",
            modifier = Modifier.align(Alignment.BottomStart).padding(start = 20.dp, bottom = 12.dp),
            color = OceanColors.TextSecondary, fontSize = 10.sp
        )
    }
}

@Composable
private fun PetPlaceholder() {
    Box(
        Modifier.size(124.dp, 110.dp).clip(RoundedCornerShape(24.dp)).background(OceanColors.Accent.copy(alpha = 0.85f)),
        contentAlignment = Alignment.Center
    ) {
        Text("🐢", fontSize = 48.sp)
    }
}

@Composable
private fun CharacterSlot(label: String, modifier: Modifier = Modifier) {
    Box(
        modifier
            .size(56.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(OceanColors.SlotOverlay)
            .border(1.5.dp, OceanColors.Info, RoundedCornerShape(28.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(label, color = OceanColors.OceanText, fontSize = 11.sp, fontWeight = FontWeight.Medium)
    }
}
