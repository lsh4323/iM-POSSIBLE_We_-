package com.example.myapplication.ui.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun CharacterImage(
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier,
    contentDescription: String = "캐릭터",
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Image(
        painter = painterResource(id = imageRes),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        modifier = modifier.clickable(
            enabled = enabled,
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
        )
    )
}