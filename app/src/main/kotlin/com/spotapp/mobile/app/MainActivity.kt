package com.spotapp.mobile.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.spotapp.mobile.app.di.AppModule
import com.spotapp.mobile.app.navigation.Destinations
import com.spotapp.mobile.app.navigation.NavGraph
import com.spotapp.mobile.app.theme.SpotTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val appModule: AppModule by lazy { (applicationContext as SpotApplication).appModule }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appModule.data.firestoreService.subscribeToRealtimeUpdates()

        lifecycleScope.launch {
            val startDestination =
                Destinations.startDestination(appModule.domain.getCurrentAppStateUseCase)

            setContent {
                SpotTheme {
                    NavGraph(
                        appModule = appModule,
                        startDestination = startDestination,
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
}
