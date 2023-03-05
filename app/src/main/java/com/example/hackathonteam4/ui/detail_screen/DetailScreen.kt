package com.example.hackathonteam4.ui.detail_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.hackathonteam4.ui.theme.HackathonTeam4Theme

@Composable
fun DetailScreen(navController: NavController) {
    HackathonTeam4Theme {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Detail")
        }
    }

}