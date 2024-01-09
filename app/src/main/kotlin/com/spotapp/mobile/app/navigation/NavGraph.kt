package com.spotapp.mobile.app.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.spotapp.mobile.app.di.AppModule
import com.spotapp.mobile.ui.ext.createViewModel
import com.spotapp.mobile.ui.feature.auth.signin.SignInScreen
import com.spotapp.mobile.ui.feature.auth.signin.SignInViewModel
import com.spotapp.mobile.ui.feature.auth.signup.SignUpScreen
import com.spotapp.mobile.ui.feature.auth.signup.SignUpViewModel
import com.spotapp.mobile.ui.feature.home.HomeScreen
import com.spotapp.mobile.ui.feature.home.HomeViewModel
import com.spotapp.mobile.ui.feature.settings.SettingsScreen
import com.spotapp.mobile.ui.feature.settings.SettingsViewModel
import com.spotapp.mobile.ui.feature.welcome.WelcomeScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    appModule: AppModule,
    startDestination: String,
    navController: NavHostController
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            AppDrawer(
                route = currentRoute,
                navigateToHome = { navController.navigate(Destinations.home()) },
                navigateToSettings = { navController.navigate(Destinations.settings()) },
                closeDrawer = { scope.launch { drawerState.close() } }
            )
        },
        drawerState = drawerState,
    ) {
        Scaffold(
            topBar = {
                if (!allowNavigation(currentRoute)) {
                    TopAppBar(
                        title = { Text(text = currentRoute) },
                        modifier = Modifier.fillMaxWidth(),
                        navigationIcon = {
                            IconButton(
                                onClick = { scope.launch { drawerState.open() } },
                                content = {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = null
                                    )
                                }
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                }
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                composable(route = Destinations.welcome()) {
                    WelcomeScreen(
                        onGoToSignIn = { navController.navigate(route = Destinations.signIn()) },
                        onGoToSignUp = { navController.navigate(route = Destinations.signUp()) }
                    )
                }

                composable(route = Destinations.signIn()) {
                    SignInScreen(
                        viewModel = createViewModel {
                            SignInViewModel(
                                usersRepository = appModule.data.usersRepository
                            )
                        },
                        onGoBack = {
                            navController.navigate(Destinations.welcome()) {
                                popUpTo(0)
                            }
                        },
                        onGoToHome = {
                            navController.navigate(Destinations.home())
                        }
                    )
                }

                composable(route = Destinations.signUp()) {
                    SignUpScreen(
                        viewModel = createViewModel {
                            SignUpViewModel(usersRepository = appModule.data.usersRepository)
                        },
                        onGoBack = {
                            navController.navigate(Destinations.welcome()) {
                                popUpTo(0)
                            }
                        },
                        onGoToSignIn = { navController.navigate(route = Destinations.signIn()) }
                    )
                }

                composable(route = Destinations.home()) {
                    HomeScreen(
                        viewModel = createViewModel {
                            HomeViewModel()
                        }
                    )
                }

                composable(route = Destinations.settings()) {
                    SettingsScreen(
                        viewModel = createViewModel {
                            SettingsViewModel(
                                usersRepository = appModule.data.usersRepository
                            )
                        },
                        onGoToWelcome = {
                            navController.navigate(Destinations.welcome()) {
                                popUpTo(0)
                            }
                        },
                        paddingValues = paddingValues
                    )
                }
            }
        }
    }
}

private fun allowNavigation(route: String): Boolean {
    return route == Destinations.welcome() || route == Destinations.signIn() || route == Destinations.signUp()
}
