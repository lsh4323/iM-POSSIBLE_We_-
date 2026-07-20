package com.example.myapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme

private val OceanColorScheme = lightColorScheme(
    primary = OceanColors.Accent,
    onPrimary = OceanColors.TextPrimary,
    secondary = OceanColors.Info,
    background = OceanColors.Background,
    onBackground = OceanColors.TextPrimary,
    surface = OceanColors.Surface,
    onSurface = OceanColors.TextPrimary,
    outline = OceanColors.Border,
)

@Composable
fun OceanFriendsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // 디자인은 라이트 전용 — 항상 라이트 스킴 사용
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = OceanColorScheme,
        typography = OceanTypography,
        content = content
    )
}
