package com.example.myapplication.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.ui.menu.MenuRoute
import com.example.myapplication.ui.menu.MenuViewModel
import com.example.myapplication.ui.screens.developer.DeveloperModeScreen
import com.example.myapplication.ui.screens.home.HomeRoute
import com.example.myapplication.ui.screens.home.HomeViewModel
import com.example.myapplication.ui.screens.house.HouseRoute
import com.example.myapplication.ui.screens.house.HouseViewModel
import com.example.myapplication.ui.screens.analysis.AnalysisScreen
import com.example.myapplication.ui.screens.certificate.CertificateScreen
import com.example.myapplication.ui.screens.donate.DonateScreen
import com.example.myapplication.ui.screens.donatecomplete.DonateCompleteScreen
import com.example.myapplication.ui.screens.grade.GradeScreen
import com.example.myapplication.ui.screens.myocean.MyOceanScreen
import com.example.myapplication.ui.screens.product.ProductScreen
import com.example.myapplication.ui.screens.summary.SummaryScreen
import com.example.myapplication.ui.screens.together.TogetherScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    // Home, Menu, DeveloperMode에서 공유하는 ViewModel
    val homeViewModel: HomeViewModel = viewModel()

    fun onSelectTab(tab: BottomTab) {
        navController.navigate(tab.route) {
            popUpTo(Screen.Home.route) {
                saveState = true
            }

            launchSingleTop = true
            restoreState = true
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        /*
         * 우리가 만든 단디 홈 화면
         */
        composable(
            route = Screen.Home.route,
            exitTransition = {
                when (targetState.destination.route) {
                    Screen.Menu.route,
                    Screen.DeveloperMode.route -> ExitTransition.None

                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    Screen.Menu.route,
                    Screen.DeveloperMode.route -> EnterTransition.None

                    else -> null
                }
            }
        ) {
            HomeRoute(
                viewModel = homeViewModel,
                onNavigateToHouse = {
                    navController.navigate(Screen.House.route)
                },
                onNavigateToMenu = {
                    navController.navigate(Screen.Menu.route)
                },
                onSelectTab = ::onSelectTab
            )
        }

        /*
         * 이글루 내부
         */
        composable(
            route = Screen.House.route
        ) {
            val houseViewModel: HouseViewModel = viewModel()

            HouseRoute(
                viewModel = houseViewModel
            )
        }

        /*
         * 메뉴
         */
        composable(
            route = Screen.Menu.route,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                ExitTransition.None
            }
        ) {
            val menuViewModel: MenuViewModel = viewModel()

            MenuRoute(
                viewModel = menuViewModel,
                onBackClick = {
                    navController.popBackStack()
                },
                onDeveloperModeClick = {
                    navController.navigate(Screen.DeveloperMode.route)
                }
            )
        }

        /*
         * 개발자 모드
         */
        composable(
            route = Screen.DeveloperMode.route,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                ExitTransition.None
            }
        ) {
            DeveloperModeScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onLetterAlarmClick = {
                    homeViewModel.enableAlarm()
                    navController.navigateToHome()
                },
                onAnimalIntroductionClick = {
                    homeViewModel.showAnimalIntroduction()
                    navController.navigateToHome()
                }
            )
        }

        /*
         * 내 바다
         */
        composable(
            route = Routes.MY_OCEAN
        ) {
            MyOceanScreen(
                onOpenAnalysis = {
                    navController.navigate(Routes.ANALYSIS)
                },
                onOpenGrade = {
                    navController.navigate(Routes.GRADE)
                },
                onSelectTab = ::onSelectTab
            )
        }

        /*
         * 이번 주 소비 분석
         */
        composable(
            route = Routes.ANALYSIS
        ) {
            AnalysisScreen(
                onBack = {
                    navController.popBackStack()
                },
                onOpenSummary = {
                    navController.navigate(Routes.SUMMARY)
                }
            )
        }

        /*
         * 적립 요약
         */
        composable(
            route = Routes.SUMMARY
        ) {
            SummaryScreen(
                onBack = {
                    navController.popBackStack()
                },
                onDonate = {
                    navController.navigate(Routes.DONATE)
                },
                onBackToOcean = {
                    navController.navigateToMyOcean()
                }
            )
        }

        /*
         * 기부하기
         */
        composable(
            route = Routes.DONATE
        ) {
            DonateScreen(
                onBack = {
                    navController.popBackStack()
                },
                onSubmit = { amount ->
                    navController.navigate(
                        "${Routes.DONATE_COMPLETE}/$amount"
                    )
                }
            )
        }

        /*
         * 기부 완료
         */
        composable(
            route = "${Routes.DONATE_COMPLETE}/{amount}",
            arguments = listOf(
                navArgument("amount") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val amount = backStackEntry.arguments
                ?.getInt("amount")
                ?: 0

            DonateCompleteScreen(
                amountFish = amount,
                onOpenCertificate = {
                    navController.navigate(Routes.CERTIFICATE)
                },
                onBackToOcean = {
                    navController.navigateToMyOcean()
                }
            )
        }

        /*
         * ESG 활동 증명서
         */
        composable(
            route = Routes.CERTIFICATE
        ) {
            CertificateScreen(
                onShare = {
                    // 추후 공유 기능 구현
                },
                onSelectTab = ::onSelectTab
            )
        }

        /*
         * 등급
         */
        composable(
            route = Routes.GRADE
        ) {
            GradeScreen(
                onBack = {
                    navController.popBackStack()
                },
                onOpenProduct = {
                    navController.navigate(Routes.PRODUCT)
                }
            )
        }

        /*
         * 전용상품
         */
        composable(
            route = Routes.PRODUCT
        ) {
            ProductScreen(
                onSubmit = {
                    // 추후 가입 기능 구현
                },
                onSelectTab = ::onSelectTab
            )
        }

        /*
         * 함께하는 바다
         */
        composable(
            route = Routes.TOGETHER
        ) {
            TogetherScreen(
                onShare = {
                    // 추후 공유 기능 구현
                },
                onDonate = {
                    navController.navigate(Routes.DONATE)
                },
                onSelectTab = ::onSelectTab
            )
        }
    }
}

private fun NavHostController.navigateToHome() {
    navigate(Screen.Home.route) {
        popUpTo(Screen.Home.route) {
            inclusive = false
        }

        launchSingleTop = true
    }
}

private fun NavHostController.navigateToMyOcean() {
    navigate(Routes.MY_OCEAN) {
        popUpTo(Screen.Home.route) {
            inclusive = false
        }

        launchSingleTop = true
    }
}
