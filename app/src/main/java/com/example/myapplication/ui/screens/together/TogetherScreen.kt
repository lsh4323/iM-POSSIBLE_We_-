package com.example.myapplication.ui.screens.together

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.myapplication.ui.components.AppTopBar
import com.example.myapplication.ui.components.Chip
import com.example.myapplication.ui.components.ChipStyle
import com.example.myapplication.ui.components.OceanBottomBar
import com.example.myapplication.ui.components.OceanProgressBar
import com.example.myapplication.ui.components.PrimaryButton
import com.example.myapplication.ui.components.StatusBar
import com.example.myapplication.ui.theme.OceanColors

@Composable
fun TogetherScreen(
    onShare: () -> Unit,
    onDonate: () -> Unit,
    onSelectTab: (BottomTab) -> Unit,
    viewModel: TogetherViewModel = viewModel(factory = TogetherViewModel.Factory),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(Modifier.fillMaxSize().background(OceanColors.Background)) {
        StatusBar()
        AppTopBar(
            title = "함께하는 바다",
            trailing = {
                Text("공유", modifier = Modifier.clickable { onShare() }, color = OceanColors.TextSecondary, fontSize = 14.sp)
            }
        )

        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            // 캠페인 카드
            Column(
                Modifier.fillMaxWidth().padding(20.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(OceanColors.Surface)
                    .border(1.dp, OceanColors.Border, RoundedCornerShape(12.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(state.campaignName, color = OceanColors.TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Chip(state.basis, ChipStyle.GrayFilled, fontSize = 10)
                }
                OceanProgressBar(progress = state.progress, heightDp = 10)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text("${(state.progress * 100).toInt()}%", color = OceanColors.Accent, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("${state.raised} / ${state.goal}", color = OceanColors.TextSecondary, fontSize = 12.sp)
                    }
                    Text("참여 ${"%,d".format(state.participants)}명", color = OceanColors.TextSecondary, fontSize = 12.sp)
                }
            }

            // 참여자 바다 (플레이스홀더 연출)
            ParticipantsScene(state.participants)

            // 내가 보낸 것
            Column(
                Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            ) {
                Row(
                    Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(OceanColors.Surface)
                        .border(1.dp, OceanColors.Border, RoundedCornerShape(12.dp))
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("내가 보낸 것", color = OceanColors.TextSecondary, fontSize = 12.sp)
                        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("물고기 ${state.myFish}마리", color = OceanColors.TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                            Text("· ${"%,d".format(state.myWon)}원", color = OceanColors.TextSecondary, fontSize = 12.sp)
                        }
                    }
                    Text("증명서에 기록됨", color = OceanColors.TextSecondary, fontSize = 12.sp)
                }
                Text(
                    "· 혼자서는 닿지 않는 사업 단위 금액을, 함께 모아 완성합니다.",
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = OceanColors.TextSecondary, fontSize = 12.sp
                )
            }
        }

        Box(Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 12.dp)) {
            PrimaryButton("기부하러 가기", onDonate, height = 47)
        }
        OceanBottomBar(selected = BottomTab.DONATE, onSelect = onSelectTab)
    }
}

@Composable
private fun ParticipantsScene(participants: Int) {
    Box(
        Modifier.fillMaxWidth().height(300.dp)
            .background(Brush.verticalGradient(listOf(OceanColors.OceanTop, OceanColors.OceanBottom)))
    ) {
        Text(
            "${"%,d".format(participants)}명의 친구들이 함께 헤엄치고 있어요",
            modifier = Modifier.padding(start = 20.dp, top = 16.dp),
            color = OceanColors.OceanText, fontSize = 13.sp, fontWeight = FontWeight.Medium
        )
        // 내 펫 강조 (가운데)
        Column(
            Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier.size(78.dp).clip(RoundedCornerShape(50)).background(OceanColors.SlotOverlay),
                contentAlignment = Alignment.Center
            ) { Text("🐢", fontSize = 40.sp) }
            Chip("나", ChipStyle.BlueFilled)
        }
        // 주변 참여자 펫들 (플레이스홀더)
        listOf("🐠", "🐟", "🐡", "🦑", "🐙", "🦐").forEachIndexed { i, emoji ->
            val alignments = listOf(
                Alignment.TopStart, Alignment.TopEnd, Alignment.CenterStart,
                Alignment.CenterEnd, Alignment.BottomStart, Alignment.BottomEnd
            )
            Text(
                emoji,
                modifier = Modifier.align(alignments[i]).padding(24.dp),
                fontSize = (22 + i * 2).sp
            )
        }
        Text(
            "목표를 채우면 이 바다가 실제 갯벌 복원으로 이어집니다",
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp),
            color = OceanColors.TextSecondary, fontSize = 12.sp
        )
    }
}
