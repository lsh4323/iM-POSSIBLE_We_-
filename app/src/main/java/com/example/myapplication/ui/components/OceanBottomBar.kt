package com.example.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.navigation.BottomTab
import com.example.myapplication.ui.theme.OceanColors
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.myapplication.R
/**
 * 하단 내비게이션 바 (5개 탭).
 * 아이콘은 디자인상 원형 플레이스홀더 — 공식 에셋 확보 후 교체.
 */
@Composable
fun OceanBottomBar(
    selected: BottomTab,
    onSelect: (BottomTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(OceanColors.Surface)
            .border(1.dp, OceanColors.Border)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BottomTab.entries.forEach { tab ->
            BottomBarItem(
                tab = tab,
                isSelected = tab == selected,
                isCenter = tab == BottomTab.HOME,
                onClick = { onSelect(tab) }
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    tab: BottomTab,
    isSelected: Boolean,
    isCenter: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (isCenter) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(OceanColors.Accent),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.dandi),
                    contentDescription = "홈",
                    modifier = Modifier.size(36.dp),
                    contentScale = ContentScale.Fit
                )
            }
        } else {
            val dotColor =
                if (isSelected) {
                    OceanColors.Accent
                } else {
                    OceanColors.Info.copy(alpha = 0.55f)
                }

            CirclePlaceholder(
                sizeDp = 20,
                color = dotColor
            )

            Text(
                text = tab.label,
                color = if (isSelected) {
                    OceanColors.TextPrimary
                } else {
                    OceanColors.TextSecondary
                },
                fontSize = 10.sp,
                fontWeight = if (isSelected) {
                    FontWeight.Medium
                } else {
                    FontWeight.Normal
                }
            )
        }
    }
}
