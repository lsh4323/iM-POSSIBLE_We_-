package com.example.myapplication.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Figma 디자인에서 추출한 색상 토큰.
 * "바다친구들" 해양 보전 ESG 금융 앱.
 */
object OceanColors {
    // Base
    val Background = Color(0xFFFFFFFF)
    val Surface = Color(0xFFF2FAFD)      // StatusBar / Card / BottomBar 배경
    val Border = Color(0xFFDCE7ED)       // 구분선 / 카드 테두리

    // Text
    val TextPrimary = Color(0xFF062B45)   // 제목 · 강조
    val TextSecondary = Color(0xFF5A7184) // 본문 · 보조
    val TextTertiary = Color(0xFFA9B6C1)  // 비활성 · 힌트

    // Brand / Accent
    val Accent = Color(0xFF10C4A9)        // 주요 CTA (teal)
    val PointGreen = Color(0xFF0B8A78)    // +포인트 적립 수치
    val Info = Color(0xFF1585B5)          // AI 매칭 · 슬롯 점선

    // Ocean scene
    val OceanTop = Color(0xFFE8F6FC)
    val OceanBottom = Color(0xFF9FD8EC)
    val OceanText = Color(0xFF0E4D6E)

    // Chips / Badges
    val ChipBlueBg = Color(0xFFD6EEF7)
    val ChipBlueText = Color(0xFF0E4D6E)
    val ChipGrayBg = Color(0xFFEEF2F5)
    val ChipGrayText = Color(0xFF5A7184)
    val BadgeDark = Color(0xFF0E4D6E)     // "환경부 인정 · 자동" (흰 글씨)

    val SlotOverlay = Color(0x8CFFFFFF)   // rgba(255,255,255,0.55)
}
