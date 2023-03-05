package com.example.hackathonteam4.ui.mian_screen

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.hackathonteam4.ui.navigation.Screen
import com.example.hackathonteam4.ui.theme.HackathonTeam4Theme
import com.example.hackathonteam4.ui.theme.spacing
import com.example.hackathonteam4.R

@Composable
fun MainScreen(navController: NavController) {
    HackathonTeam4Theme {
        Column(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Button(
                onClick = { navController.navigate(Screen.ScannerScreen.route) },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.tf_scan_barcode))
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            Button(
                onClick = { navController.navigate(Screen.HandleInputScreen.route)},
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.input_barcode))
            }
        }
    }
}