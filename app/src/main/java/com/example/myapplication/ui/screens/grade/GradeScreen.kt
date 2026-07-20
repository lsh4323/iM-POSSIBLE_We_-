package com.example.myapplication.ui.screens.grade

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.model.GradeRate
import com.example.myapplication.ui.components.AppTopBar
import com.example.myapplication.ui.components.Chip
import com.example.myapplication.ui.components.ChipStyle
import com.example.myapplication.ui.components.OceanProgressBar
import com.example.myapplication.ui.components.PrimaryButton
import com.example.myapplication.ui.components.StatusBar
import com.example.myapplication.ui.theme.OceanColors

@Composable
fun GradeScreen(
    onBack: () -> Unit,
    onOpenProduct: () -> Unit,
    viewModel: GradeViewModel = viewModel(factory = GradeViewModel.Factory),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(Modifier.fillMaxSize().background(OceanColors.Background)) {
        StatusBar()
        AppTopBar(title = "바다지킴이 등급", onBack = onBack)

        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            // 현재 등급 헤더
            Column(
                Modifier.fillMaxWidth().padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("${state.currentGrade}등급", color = OceanColors.TextPrimary, fontSize = 34.sp, fontWeight = FontWeight.Bold)
                Text("최근 12개월 적립 ${"%,d".format(state.recentFish)}마리", color = OceanColors.TextSecondary, fontSize = 13.sp)
                OceanProgressBar(progress = state.progress, modifier = Modifier.padding(top = 4.dp))
                Text("${state.currentGrade - 1}등급까지 ${state.fishToNextGrade}마리", color = OceanColors.TextSecondary, fontSize = 12.sp)
            }

            // 등급별 우대금리
            Text(
                "등급별 우대금리",
                modifier = Modifier.padding(start = 20.dp, top = 8.dp, bottom = 8.dp),
                color = OceanColors.TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Bold
            )
            Column(Modifier.padding(horizontal = 20.dp)) {
                state.rates.forEach { RateRow(it) }
            }

            // 안내
            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Note("· 등급은 최근 12개월 적립 실적으로 산정되며 매월 1일 갱신됩니다. (1등급이 최상위)")
                Note("· 기부에 사용한 물고기는 등급에서 차감되지 않습니다.")
                Note("· 가입 시점 등급으로 기본 우대금리가 확정되며, 이후 등급이 내려가도 금리는 유지됩니다.")
            }
        }

        Box(Modifier.fillMaxWidth().padding(20.dp)) {
            PrimaryButton("${state.currentGrade}등급으로 상품 보기", onOpenProduct, height = 47)
        }
    }
}

@Composable
private fun RateRow(rate: GradeRate) {
    Row(
        Modifier.fillMaxWidth().height(36.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                "${rate.grade}등급",
                color = if (rate.isCurrent) OceanColors.TextPrimary else OceanColors.TextSecondary,
                fontSize = 14.sp,
                fontWeight = if (rate.isCurrent) FontWeight.Bold else FontWeight.Normal
            )
            if (rate.isCurrent) Chip("현재", ChipStyle.BlueFilled)
        }
        Text(rate.rate, color = OceanColors.TextPrimary, fontSize = 14.sp, fontWeight = if (rate.isCurrent) FontWeight.Bold else FontWeight.Normal)
    }
}

@Composable
private fun Note(text: String) {
    Text(text, color = OceanColors.TextSecondary, fontSize = 12.sp)
}
