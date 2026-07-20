package com.example.myapplication.ui.screens.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.myapplication.data.model.RewardSummary
import com.example.myapplication.ui.components.AppTopBar
import com.example.myapplication.ui.components.Chip
import com.example.myapplication.ui.components.ChipStyle
import com.example.myapplication.ui.components.OceanProgressBar
import com.example.myapplication.ui.components.PrimaryButton
import com.example.myapplication.ui.components.SecondaryButton
import com.example.myapplication.ui.components.StatusBar
import com.example.myapplication.ui.theme.OceanColors

@Composable
fun SummaryScreen(
    onBack: () -> Unit,
    onDonate: () -> Unit,
    onBackToOcean: () -> Unit,
    viewModel: SummaryViewModel = viewModel(factory = SummaryViewModel.Factory),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val summary = state.summary ?: return

    Column(Modifier.fillMaxSize().background(OceanColors.Background)) {
        StatusBar()
        AppTopBar(title = "이번 주 적립 현황", onBack = onBack)

        Hero(summary, Modifier.weight(1f))
        GradeCard(summary)

        Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            PrimaryButton("기부하러 가기", onDonate, height = 47)
            SecondaryButton("내 바다로 돌아가기", onBackToOcean)
        }
    }
}

@Composable
private fun Hero(summary: RewardSummary, modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .background(Brush.verticalGradient(listOf(OceanColors.OceanTop, OceanColors.OceanBottom))),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(40.dp))
        Text("🐟", fontSize = 56.sp)
        Spacer(Modifier.height(16.dp))
        Text("물고기 ${summary.totalFish}마리 적립!", color = OceanColors.TextPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Text("마이데이터·탄소중립포인트 연동으로 자동 적립됐어요", color = OceanColors.TextSecondary, fontSize = 11.sp)
        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Chip("환경부 인정 ${summary.envCertified}", ChipStyle.BlueFilled)
            Chip("AI 매칭 ${summary.aiMatched}", ChipStyle.GrayFilled)
        }
    }
}

@Composable
private fun GradeCard(summary: RewardSummary) {
    Box(Modifier.fillMaxWidth().padding(20.dp)) {
        Column(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(OceanColors.Surface)
                .border(1.dp, OceanColors.Border, RoundedCornerShape(12.dp))
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("바다지킴이 ${summary.grade}등급", color = OceanColors.TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Text("${summary.grade - 1}등급까지 ${summary.fishToNextGrade}마리", color = OceanColors.TextSecondary, fontSize = 11.sp)
            }
            OceanProgressBar(progress = summary.gradeProgress)
            Text("최근 12개월 적립 실적 기준 · 매월 1일 갱신", color = OceanColors.TextSecondary, fontSize = 11.sp)
        }
    }
}
