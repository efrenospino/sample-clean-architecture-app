package com.spotapp.mobile.app.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.spotapp.mobile.app.di.AppStateManager
import com.spotapp.mobile.domain.usecases.AuthenticateNewAnonymousUser
import com.spotapp.mobile.domain.usecases.AuthenticateNewUserWithEmail
import com.spotapp.mobile.ui.feature.auth.AuthScreen
import com.spotapp.mobile.ui.feature.auth.AuthViewModel
import com.spotapp.mobile.ui.feature.home.HomeScreen

@Composable
fun NavGraph(
    startDestination: String,
    navController: NavHostController,
    appStateManager: AppStateManager,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

        composable(route = Destinations.home()) {
            HomeScreen(
                viewModel = viewModel()
            )
        }

        composable(route = Destinations.auth()) {
            AuthScreen(viewModel = viewModel(
                factory = AuthViewModel.factory(
                    authenticateNewAnonymousUser = AuthenticateNewAnonymousUser(usersRepository = appStateManager.usersRepository),
                    authenticateNewUserWithEmail = AuthenticateNewUserWithEmail(usersRepository = appStateManager.usersRepository),
                )
            ), navigateToHome = {
                navController.navigate(Destinations.home()) {
                    popUpTo(0)
                }
            })
        }
    }
}
