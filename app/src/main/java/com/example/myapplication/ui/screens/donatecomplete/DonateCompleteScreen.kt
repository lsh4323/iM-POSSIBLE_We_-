package com.example.myapplication.ui.screens.donatecomplete

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.Chip
import com.example.myapplication.ui.components.ChipStyle
import com.example.myapplication.ui.components.PrimaryButton
import com.example.myapplication.ui.components.SecondaryButton
import com.example.myapplication.ui.components.StatusBar
import com.example.myapplication.ui.theme.OceanColors

/**
 * 기부 완료 화면. 기부 수량은 이전 화면에서 전달받는다(nav argument).
 */
@Composable
fun DonateCompleteScreen(
    amountFish: Int,
    onOpenCertificate: () -> Unit,
    onBackToOcean: () -> Unit,
) {
    val amountWon = amountFish * 10
    Column(Modifier.fillMaxSize().background(OceanColors.Background)) {
        StatusBar()

        Column(
            Modifier.weight(1f).fillMaxWidth().padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(30.dp))
            Text("바다로 보냈어요!", color = OceanColors.TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            Text("물고기 ${"%,d".format(amountFish)}마리 → 바다거북 구조", color = OceanColors.TextSecondary, fontSize = 13.sp)

            // Lv.2 → Lv.3 연출 (플레이스홀더)
            Spacer(Modifier.height(24.dp))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("🐢", fontSize = 44.sp)
                Text("→", color = OceanColors.TextSecondary, fontSize = 22.sp)
                Text("🐢", fontSize = 64.sp)
            }
            Spacer(Modifier.height(16.dp))
            Chip("바다거북 Lv.2 → Lv.3", ChipStyle.BlueFilled, fontSize = 11)
            Spacer(Modifier.height(12.dp))
            Row(
                Modifier.clip(RoundedCornerShape(20.dp)).background(OceanColors.Surface).padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text("🪸", fontSize = 16.sp)
                Text("산호 장식 획득", color = OceanColors.TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
            }

            // 임팩트 원장 기록 카드
            Spacer(Modifier.height(24.dp))
            Column(
                Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(OceanColors.Surface)
                    .border(1.dp, OceanColors.Border, RoundedCornerShape(12.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("임팩트 원장에 기록됨", color = OceanColors.TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Text(
                    "OO 해양동물전문구조·치료기관에 ${"%,d".format(amountWon)}원 전달 · 기부 실행 주체 iM뱅크",
                    color = OceanColors.TextSecondary, fontSize = 12.sp
                )
                Text("기부금영수증 발급 · 증명서에서 확인 가능", color = OceanColors.TextSecondary, fontSize = 11.sp)
            }
        }

        Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            PrimaryButton("증명서 보기", onOpenCertificate, height = 47)
            SecondaryButton("내 바다로 돌아가기", onBackToOcean)
        }
    }
}
