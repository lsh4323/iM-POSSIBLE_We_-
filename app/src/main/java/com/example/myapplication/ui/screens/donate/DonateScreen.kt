package com.example.myapplication.ui.screens.donate

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
import com.example.myapplication.data.model.DonationEffect
import com.example.myapplication.data.model.DonationTarget
import com.example.myapplication.ui.components.AppTopBar
import com.example.myapplication.ui.components.Chip
import com.example.myapplication.ui.components.ChipStyle
import com.example.myapplication.ui.components.PrimaryButton
import com.example.myapplication.ui.components.StatusBar
import com.example.myapplication.ui.theme.OceanColors

@Composable
fun DonateScreen(
    onBack: () -> Unit,
    onSubmit: (amountFish: Int) -> Unit,
    viewModel: DonateViewModel = viewModel(factory = DonateViewModel.Factory),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(Modifier.fillMaxSize().background(OceanColors.Background)) {
        StatusBar()
        AppTopBar(title = "바다로 보내기", onBack = onBack)

        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            // 보유 물고기
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("보유 물고기", color = OceanColors.TextSecondary, fontSize = 13.sp)
                AmountText("${"%,d".format(state.heldFish)}마리", "· ${"%,d".format(state.heldWon)}원")
            }

            // 어디로 보낼까요
            SectionTitle("어디로 보낼까요?")
            Column(Modifier.padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                state.targets.forEach { target ->
                    TargetRow(target, target.id == state.selectedTargetId) { viewModel.selectTarget(target.id) }
                }
            }

            // 얼마나 보낼까요 (슬라이더)
            Row(
                Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("얼마나 보낼까요?", color = OceanColors.TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                AmountText("${"%,d".format(state.amountFish)}마리", "· ${"%,d".format(state.amountWon)}원", big = true)
            }
            Slider(
                value = state.amountFish.toFloat(),
                onValueChange = { viewModel.setAmount(it.toInt()) },
                valueRange = 0f..state.heldFish.toFloat().coerceAtLeast(1f),
                colors = SliderDefaults.colors(
                    thumbColor = OceanColors.Accent,
                    activeTrackColor = OceanColors.Accent,
                    inactiveTrackColor = OceanColors.Border
                ),
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("0", color = OceanColors.TextSecondary, fontSize = 12.sp)
                Text("최대 ${"%,d".format(state.heldFish)}마리", color = OceanColors.TextSecondary, fontSize = 12.sp)
            }

            // 이만큼 보내면 (효과)
            SectionTitle("이만큼 보내면", top = 24)
            Column(
                Modifier.padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(OceanColors.Surface)
                    .border(1.dp, OceanColors.Border, RoundedCornerShape(12.dp))
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                state.effects.forEach { EffectRow(it) }
            }
            Text(
                "· 이 테마에 처음 보내면 펫 친구가 지급됩니다.\n· 기부는 iM뱅크가 실행하고 기부금영수증이 발급됩니다.",
                modifier = Modifier.padding(20.dp),
                color = OceanColors.TextSecondary, fontSize = 11.sp
            )
        }

        // CTA
        Box(Modifier.fillMaxWidth().padding(20.dp)) {
            PrimaryButton("${"%,d".format(state.amountFish)}마리 바다로 보내기", { onSubmit(state.amountFish) }, height = 47)
        }
    }
}

@Composable
private fun SectionTitle(text: String, top: Int = 8) {
    Text(
        text,
        modifier = Modifier.padding(start = 20.dp, top = top.dp, bottom = 12.dp),
        color = OceanColors.TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Bold
    )
}

@Composable
private fun AmountText(fish: String, won: String, big: Boolean = false) {
    Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(fish, color = OceanColors.TextPrimary, fontSize = if (big) 20.sp else 15.sp, fontWeight = FontWeight.Bold)
        Text(won, color = OceanColors.TextSecondary, fontSize = 11.sp)
    }
}

@Composable
private fun TargetRow(target: DonationTarget, selected: Boolean, onClick: () -> Unit) {
    Row(
        Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(if (selected) OceanColors.ChipBlueBg else OceanColors.Surface)
            .border(
                1.dp,
                if (selected) OceanColors.Accent else OceanColors.Border,
                RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 12.dp, vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // 라디오 (플레이스홀더 원)
        Box(
            Modifier.size(16.dp).clip(RoundedCornerShape(50))
                .border(if (selected) 5.dp else 2.dp, if (selected) OceanColors.Accent else OceanColors.Border, RoundedCornerShape(50))
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(target.name, color = OceanColors.TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
            Chip(target.basis, ChipStyle.GrayFilled, fontSize = 10)
        }
    }
}

@Composable
private fun EffectRow(effect: DonationEffect) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Box(
            Modifier.size(28.dp).clip(RoundedCornerShape(50))
                .background(if (effect.achieved) OceanColors.Accent.copy(alpha = 0.2f) else OceanColors.ChipGrayBg),
            contentAlignment = Alignment.Center
        ) { Text(if (effect.achieved) "🐚" else "🪸", fontSize = 14.sp) }
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(effect.title, color = OceanColors.TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
            Text(effect.description, color = OceanColors.TextSecondary, fontSize = 11.sp)
        }
        Chip(if (effect.achieved) "달성" else "미달", if (effect.achieved) ChipStyle.BlueFilled else ChipStyle.GrayFilled)
    }
}
