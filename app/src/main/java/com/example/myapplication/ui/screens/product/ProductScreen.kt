package com.example.myapplication.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.myapplication.data.model.RateComponent
import com.example.myapplication.ui.components.AppTopBar
import com.example.myapplication.ui.components.Chip
import com.example.myapplication.ui.components.ChipStyle
import com.example.myapplication.ui.components.OceanBottomBar
import com.example.myapplication.ui.components.PrimaryButton
import com.example.myapplication.ui.components.StatusBar
import com.example.myapplication.navigation.BottomTab
import com.example.myapplication.ui.theme.OceanColors

@Composable
fun ProductScreen(
    onSubmit: () -> Unit,
    onSelectTab: (BottomTab) -> Unit,
    viewModel: ProductViewModel = viewModel(factory = ProductViewModel.Factory),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(Modifier.fillMaxSize().background(OceanColors.Background)) {
        StatusBar()
        AppTopBar(title = state.productName)

        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            // 대표 금리
            Column(
                Modifier.fillMaxWidth().padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Chip("바다지킴이 ${state.grade}등급 적용", ChipStyle.BlueFilled)
                Row(verticalAlignment = Alignment.Bottom) {
                    Text("연 ", color = OceanColors.TextPrimary, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                    Text(state.totalRate, color = OceanColors.Accent, fontSize = 46.sp, fontWeight = FontWeight.Bold)
                    Text(" %", color = OceanColors.TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
                Text(state.condition, color = OceanColors.TextSecondary, fontSize = 12.sp)
            }

            // 금리 구성
            Text(
                "금리 구성",
                modifier = Modifier.padding(start = 20.dp, top = 8.dp, bottom = 8.dp),
                color = OceanColors.TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Bold
            )
            Column(Modifier.padding(horizontal = 20.dp)) {
                state.components.forEachIndexed { index, c ->
                    RateComponentRow(c)
                    if (index < state.components.lastIndex) {
                        Box(Modifier.fillMaxWidth().height(1.dp).background(OceanColors.Border))
                    }
                }
            }

            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("· 등급 산정에 쓰이는 실적은 기부로 차감되지 않습니다.", color = OceanColors.TextSecondary, fontSize = 12.sp)
                Text("· 수신 자금은 iM 녹색여신 재원으로 운용됩니다.", color = OceanColors.TextSecondary, fontSize = 12.sp)
            }
        }

        Box(Modifier.fillMaxWidth().padding(20.dp)) {
            PrimaryButton("가입하기", onSubmit, height = 47)
        }
        OceanBottomBar(selected = BottomTab.PRODUCT, onSelect = onSelectTab)
    }
}

@Composable
private fun RateComponentRow(c: RateComponent) {
    Row(
        Modifier.fillMaxWidth().padding(vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(c.label, color = OceanColors.TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            if (c.sublabel != null) {
                Text(c.sublabel, color = OceanColors.TextSecondary, fontSize = 11.sp)
            }
        }
        Text(c.value, color = OceanColors.TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}
