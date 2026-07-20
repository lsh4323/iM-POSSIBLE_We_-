package com.example.myapplication.ui.screens.certificate

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.AppTopBar
import com.example.myapplication.ui.components.Chip
import com.example.myapplication.ui.components.ChipStyle
import com.example.myapplication.ui.components.OceanBottomBar
import com.example.myapplication.ui.components.StatusBar
import com.example.myapplication.navigation.BottomTab
import com.example.myapplication.ui.theme.OceanColors

private data class CertSection(
    val title: String,
    val badge: String,
    val badgeStyle: ChipStyle,
    val lines: List<String>,
)

private val certSections = listOf(
    CertSection(
        "① 실천 실적", "환경부 공인", ChipStyle.BlueFilled,
        listOf(
            "전자영수증 23건 · 텀블러 12건 · 다회용기 8건 · 로컬푸드 4건",
            "근거: 탄소중립포인트 녹색생활실천 (가입확인서 연동)"
        )
    ),
    CertSection(
        "② 해양 보전 기부", "기부금영수증", ChipStyle.BlueFilled,
        listOf(
            "OO 해양동물전문구조·치료기관 8,000원",
            "OO 갯벌복원사업 4,000원",
            "기부 실행 주체: iM뱅크"
        )
    ),
    CertSection(
        "③ 자금이 도달한 활동", "K-Taxonomy 매핑", ChipStyle.GrayFilled,
        listOf(
            "생물다양성 보전 › 생물종 보호·보전·복원",
            "생물다양성 보전 › 육상 및 해양 생태계 보호·보전·복원",
            "※ 본 매핑은 위 기관의 활동에 대한 것이며, 개인 소비에 대한 인증이 아닙니다."
        )
    ),
    CertSection(
        "④ 참고", "공인 환산 아님", ChipStyle.GrayFilled,
        listOf(
            "로컬푸드 직매장 이용 8건 (지역농산물법상 직매장 목록 대조)",
            "환경 성과로 환산되지 않는 보완 지표입니다."
        )
    ),
)

@Composable
fun CertificateScreen(
    onShare: () -> Unit,
    onSelectTab: (BottomTab) -> Unit,
) {
    Column(Modifier.fillMaxSize().background(OceanColors.Background)) {
        StatusBar()
        AppTopBar(
            title = "ESG 활동 증명서",
            trailing = {
                Text("공유", modifier = Modifier.clickable { onShare() }, color = OceanColors.TextSecondary, fontSize = 14.sp)
            }
        )

        Column(
            Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(16.dp)
        ) {
            Column(
                Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(OceanColors.Surface)
                    .border(1.dp, OceanColors.Border, RoundedCornerShape(12.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // 문서 헤더
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("iM 바다친구들 ESG 활동 증명서", color = OceanColors.TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Text("발급 2026-BF-000847 · 2026.01.01~06.30 · 홍길동", color = OceanColors.TextSecondary, fontSize = 11.sp)
                }
                Box(Modifier.fillMaxWidth().height(1.dp).background(OceanColors.Border))

                certSections.forEach { SectionBlock(it) }

                // 발급 · QR
                Row(
                    Modifier.fillMaxWidth().padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("발급 iM뱅크 · 원장 조회", color = OceanColors.TextSecondary, fontSize = 11.sp)
                    Box(
                        Modifier.size(44.dp).clip(RoundedCornerShape(4.dp)).background(OceanColors.ChipGrayBg),
                        contentAlignment = Alignment.Center
                    ) { Text("QR", color = OceanColors.TextSecondary, fontSize = 10.sp) }
                }
            }
        }

        OceanBottomBar(selected = BottomTab.CERTIFICATE, onSelect = onSelectTab)
    }
}

@Composable
private fun SectionBlock(section: CertSection) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(section.title, color = OceanColors.TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Chip(section.badge, section.badgeStyle, fontSize = 10)
        }
        section.lines.forEach { Text(it, color = OceanColors.TextSecondary, fontSize = 11.sp) }
    }
}
