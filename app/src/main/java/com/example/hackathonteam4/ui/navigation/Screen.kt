package com.example.hackathonteam4.ui.navigation

sealed class Screen(val route:String){
    object MainScreen: Screen("main_screen")
    object ScannerScreen: Screen("scanner_screen")
    object SplashScreen: Screen("splash_screen")
    object DetailScreen: Screen("Detail_screen")
    object HandleInputScreen: Screen("handle_input_screen")
    object OnboardingScreen: Screen("onboarding_screen")
}
