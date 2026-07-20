package com.example.myapplication.navigation

object Routes {
    const val MY_OCEAN = "my_ocean"
    const val ANALYSIS = "analysis"
    const val SUMMARY = "summary"
    const val DONATE = "donate"
    const val DONATE_COMPLETE = "donate_complete"
    const val CERTIFICATE = "certificate"
    const val GRADE = "grade"
    const val PRODUCT = "product"
    const val TOGETHER = "together"
}

enum class BottomTab(
    val route: String,
    val label: String
) {
    DONATE(
        route = Routes.TOGETHER,
        label = "기부"
    ),

    PRODUCT(
        route = Routes.PRODUCT,
        label = "전용상품"
    ),

    HOME(
        route = Screen.Home.route,
        label = "홈"
    ),

    CERTIFICATE(
        route = Routes.CERTIFICATE,
        label = "증명서"
    ),

    MY_OCEAN(
        route = Routes.MY_OCEAN,
        label = "내 바다"
    )
}