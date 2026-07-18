package com.example.myapplication.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.developer.DeveloperModeScreen
import com.example.myapplication.ui.home.HomeRoute
import com.example.myapplication.ui.home.HomeViewModel
import com.example.myapplication.ui.house.HouseRoute
import com.example.myapplication.ui.house.HouseViewModel
import com.example.myapplication.ui.menu.MenuRoute
import com.example.myapplication.ui.menu.MenuViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    // 모든 화면에서 공유할 HomeViewModel
    val homeViewModel: HomeViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(
            route = Screen.Home.route,
            exitTransition = {
                if (
                    targetState.destination.route == Screen.Menu.route ||
                    targetState.destination.route == Screen.DeveloperMode.route
                ) {
                    ExitTransition.None
                } else {
                    null
                }
            },
            popEnterTransition = {
                if (
                    initialState.destination.route == Screen.Menu.route ||
                    initialState.destination.route == Screen.DeveloperMode.route
                ) {
                    EnterTransition.None
                } else {
                    null
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
                }
            )
        }

        composable(route = Screen.House.route) {
            val houseViewModel: HouseViewModel = viewModel()

            HouseRoute(
                viewModel = houseViewModel
            )
        }

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

                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                },

                onAnimalIntroductionClick = {
                    homeViewModel.showAnimalIntroduction()

                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = false
                        }

                        launchSingleTop = true
                    }
                }
            )
        }
    }
}