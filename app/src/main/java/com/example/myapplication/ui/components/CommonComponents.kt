package com.example.myapplication.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.OceanColors

/** 상단 상태바 (9:41 · 신호 표시). 실제 시스템 상태바 대체용 목업. */
@Composable
fun StatusBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(OceanColors.Surface)
            .height(44.dp)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("9:41", color = OceanColors.TextSecondary, fontSize = 11.sp, fontWeight = FontWeight.Medium)
        Text("▮▮▮ ▮", color = OceanColors.TextSecondary, fontSize = 11.sp)
    }
}

/**
 * 상세 화면 상단바.
 * @param title 가운데/좌측 제목
 * @param onBack null 이 아니면 좌측 뒤로가기 화살표 표시
 * @param trailing 우측 액션 (예: "공유")
 */
@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    trailing: (@Composable RowScope.() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (onBack != null) {
                Text(
                    "←",
                    modifier = Modifier
                        .clickable { onBack() }
                        .padding(end = 12.dp),
                    color = OceanColors.TextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Text(
                title,
                color = OceanColors.TextPrimary,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }
        if (trailing != null) trailing()
    }
}

/** 주요 CTA 버튼 (teal 채움) */
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    height: Int = 52,
    fillWidth: Boolean = true,
) {
    Box(
        modifier = modifier
            .then(if (fillWidth) Modifier.fillMaxWidth() else Modifier)
            .height(height.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(OceanColors.Accent)
            .clickable { onClick() }
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = OceanColors.TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}

/** 보조 버튼 (테두리/고스트) */
@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    height: Int = 42,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(BorderStroke(1.dp, OceanColors.Border), RoundedCornerShape(10.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = OceanColors.TextSecondary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
    }
}

/** 작은 칩/배지 스타일 */
enum class ChipStyle { BlueFilled, GrayFilled, DarkFilled, InfoOutlined }

@Composable
fun Chip(
    text: String,
    style: ChipStyle,
    modifier: Modifier = Modifier,
    fontSize: Int = 10,
) {
    val (bg, fg, border) = when (style) {
        ChipStyle.BlueFilled -> Triple(OceanColors.ChipBlueBg, OceanColors.ChipBlueText, null)
        ChipStyle.GrayFilled -> Triple(OceanColors.ChipGrayBg, OceanColors.ChipGrayText, null)
        ChipStyle.DarkFilled -> Triple(OceanColors.BadgeDark, OceanColors.Background, null)
        ChipStyle.InfoOutlined -> Triple(OceanColors.Background, OceanColors.Info, OceanColors.Info)
    }
    val shape = RoundedCornerShape(4.dp)
    Box(
        modifier = modifier
            .clip(shape)
            .background(bg)
            .then(if (border != null) Modifier.border(1.dp, border, shape) else Modifier)
            .padding(horizontal = 7.dp, vertical = 3.dp)
    ) {
        Text(text, color = fg, fontSize = fontSize.sp, fontWeight = FontWeight.Medium)
    }
}

/** 둥근 원형 플레이스홀더 (아이콘·아바타 대체). 공식 에셋 확보 후 교체. */
@Composable
fun CirclePlaceholder(sizeDp: Int, color: androidx.compose.ui.graphics.Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(sizeDp.dp)
            .clip(RoundedCornerShape(percent = 50))
            .background(color)
    )
}
