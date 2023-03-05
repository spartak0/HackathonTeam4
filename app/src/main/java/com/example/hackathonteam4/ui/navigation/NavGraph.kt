package com.example.hackathonteam4.ui.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hackathonteam4.ui.details_screen.DetailScreen
import com.example.hackathonteam4.ui.handle_input_barcode.HandleInputBarcode
import com.example.hackathonteam4.ui.mian_screen.MainScreen
import com.example.hackathonteam4.ui.onboarding.Onboarding
import com.example.hackathonteam4.ui.scanner_screen.ScannerScreen
import com.example.hackathonteam4.ui.splash_screen.SplashScreen

@Composable
fun NavGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController)
        }
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(route = Screen.ScannerScreen.route) {
            ScannerScreen(navController)
        }

        composable(route = Screen.HandleInputScreen.route) {
            HandleInputBarcode(navController)
        }
        composable(route = Screen.OnboardingScreen.route) {
            Onboarding(navController)
        }
        composable(
            route = Screen.DetailScreen.route,
        ) {
            DetailScreen(navController)
        }
    }
}

fun NavController.navigate(
    route: String,
    params: Bundle?,
) {
    this.currentBackStackEntry?.arguments?.putAll(params)
    navigate(route)
}