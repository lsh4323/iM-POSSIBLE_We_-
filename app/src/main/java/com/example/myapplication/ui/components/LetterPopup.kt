package com.example.myapplication.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign

@Composable
fun LetterPopup(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundInteractionSource = remember {
        MutableInteractionSource()
    }

    val popupInteractionSource = remember {
        MutableInteractionSource()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.35f))
            .clickable(
                interactionSource = backgroundInteractionSource,
                indication = null,
                onClick = onDismiss
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.7f)
                .background(Color.White)
                .clickable(
                    interactionSource = popupInteractionSource,
                    indication = null,
                    onClick = {
                        // 팝업 내부 클릭 시 닫히지 않도록 비워둠
                    }
                )
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "외부 모양은 편지지\n내용은 우대 금리",
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}