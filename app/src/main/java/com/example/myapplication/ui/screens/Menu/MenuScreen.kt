package com.example.myapplication.ui.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.border
import com.example.myapplication.ui.theme.OceanFriendsTheme

@Composable
fun MenuRoute(
    viewModel: MenuViewModel,
    onBackClick: () -> Unit,
    onDeveloperModeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    MenuScreen(
        onBackClick = onBackClick,
        onDeveloperModeClick = onDeveloperModeClick,
        modifier = modifier
    )
}

@Composable
fun MenuScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    onDeveloperModeClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 10.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        BackButton(
            onClick = onBackClick,
            modifier = Modifier.padding(start = 15.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        MenuItem(
            text = "개발자 모드",
            onClick = onDeveloperModeClick
        )
    }
}

@Composable
private fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(
                width = 50.dp,
                height = 30.dp
            )
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(5.dp)
            )
            .border(
                width = 2.dp,
                color = Color(0xFF00C7A9),
                shape = RoundedCornerShape(5.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        Text(
            text = "home",
            fontSize = 15.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.wrapContentSize()
        )
    }
}

@Composable
private fun MenuItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal
            )
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.Black
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=360dp,height=800dp,dpi=440"
)
@Composable
private fun MenuScreenPreview() {
    OceanFriendsTheme {
        MenuScreen(
            onBackClick = {}
        )
    }
}