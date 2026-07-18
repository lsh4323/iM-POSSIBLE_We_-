package com.example.myapplication.ui.house

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun HouseRoute(
    viewModel: HouseViewModel,
    modifier: Modifier = Modifier
) {
    HouseScreen(modifier = modifier)
}

@Composable
fun HouseScreen(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.igloo_inner),
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=360dp,height=800dp,dpi=440"
)
@Composable
private fun HouseScreenPreview() {
    MyApplicationTheme {
        HouseScreen()
    }
}