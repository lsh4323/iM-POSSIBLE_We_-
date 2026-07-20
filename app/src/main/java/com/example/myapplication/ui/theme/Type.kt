package com.example.myapplication.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * 디자인의 폰트는 Noto Sans KR.
 * 폰트 리소스를 번들하면 아래 FontFamily 를 커스텀 패밀리로 교체하세요.
 * (res/font/noto_sans_kr_*.ttf + FontFamily(Font(...)))
 */
private val AppFontFamily = FontFamily.Default

val OceanTypography = Typography(
    titleLarge = TextStyle(   // 화면 제목 "바다친구들" 19dp Bold
        fontFamily = AppFontFamily, fontWeight = FontWeight.Bold, fontSize = 19.sp
    ),
    titleMedium = TextStyle(  // 상세 헤더 17dp Bold
        fontFamily = AppFontFamily, fontWeight = FontWeight.Bold, fontSize = 17.sp
    ),
    headlineMedium = TextStyle( // 큰 수치 22dp Bold
        fontFamily = AppFontFamily, fontWeight = FontWeight.Bold, fontSize = 22.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = AppFontFamily, fontWeight = FontWeight.Medium, fontSize = 14.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = AppFontFamily, fontWeight = FontWeight.Normal, fontSize = 13.sp
    ),
    bodySmall = TextStyle(
        fontFamily = AppFontFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp
    ),
    labelSmall = TextStyle(   // 칩/캡션 10dp
        fontFamily = AppFontFamily, fontWeight = FontWeight.Medium, fontSize = 10.sp
    ),
)
