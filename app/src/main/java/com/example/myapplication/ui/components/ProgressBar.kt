package com.example.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.OceanColors

/**
 * 진행률 바. 등급/기부/모금 진행 표시에 재사용.
 * @param progress 0f..1f
 */
@Composable
fun OceanProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    heightDp: Int = 8,
    trackColor: androidx.compose.ui.graphics.Color = OceanColors.Border,
    fillColor: androidx.compose.ui.graphics.Color = OceanColors.Accent,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(heightDp.dp)
            .clip(RoundedCornerShape(heightDp.dp))
            .background(trackColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress.coerceIn(0f, 1f))
                .fillMaxHeight()
                .clip(RoundedCornerShape(heightDp.dp))
                .background(fillColor)
        )
    }
}
