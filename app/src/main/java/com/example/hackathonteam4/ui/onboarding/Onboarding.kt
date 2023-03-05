package com.example.hackathonteam4.ui.onboarding

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hackathonteam4.R
import com.example.hackathonteam4.ui.navigation.Screen
import com.example.hackathonteam4.ui.theme.HackathonTeam4Theme
import com.example.hackathonteam4.ui.theme.spacing
import com.example.hackathonteam4.utils.Constants
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Onboarding(navController: NavController) {
    val pagerState = rememberPagerState()
    val listOnBoardingText = listOf(
        stringResource(R.string.onboarding1),
        stringResource(R.string.onboarding2),
        stringResource(R.string.onboarding3),
        stringResource(R.string.onboarding4)
    )
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    HackathonTeam4Theme {
        Box(Modifier.fillMaxSize()) {
            HorizontalPager(
                count = listOnBoardingText.size, state = pagerState, modifier = Modifier.align(
                    Alignment.Center
                )
            ) { page ->
                CardOnboarding(text = listOnBoardingText[page])
            }
            FloatingActionButton(
                onClick = {
                    if (pagerState.currentPage == listOnBoardingText.size - 1) {
                        putSharedPref(true,context)
                        navController.navigate(Screen.MainScreen.route)
                    } else scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                },
                modifier = Modifier
                    .padding(
                        bottom = MaterialTheme.spacing.large,
                        end = MaterialTheme.spacing.large
                    )
                    .align(Alignment.BottomEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                    contentDescription = "arrow_forward"
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardOnboarding(text: String) {
    Card(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.large)
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text)
        }
    }
}
fun putSharedPref(value: Boolean,context:Context){
    val settings: SharedPreferences =
        context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)
    val editor = settings.edit()
    editor.putBoolean(Constants.ON_BOARDING_VAL, value)
    editor.apply()
}