package com.example.hackathonteam4.ui.splash_screen

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.hackathonteam4.R
import com.example.hackathonteam4.ui.navigation.Screen
import com.example.hackathonteam4.ui.theme.HackathonTeam4Theme
import com.example.hackathonteam4.utils.Constants
import kotlin.coroutines.coroutineContext

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    HackathonTeam4Theme {
        Handler(Looper.getMainLooper()).postDelayed({
            val sharedPref =
                context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)
            val wathingOnboarding = sharedPref.getBoolean(Constants.ON_BOARDING_VAL, false)
            navController.navigate(if (!wathingOnboarding) Screen.OnboardingScreen.route else Screen.MainScreen.route) {
                popUpTo(0)
            }
        }, 1000)
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo_surf_large),
                contentDescription = "surf_logo",
                contentScale = ContentScale.Crop
            )
        }
    }
}