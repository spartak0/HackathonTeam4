package com.example.hackathonteam4.ui.details_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hackathonteam4.domain.model.NetworkResult
import com.example.hackathonteam4.ui.theme.HackathonTeam4Theme
import com.example.hackathonteam4.ui.theme.spacing
import com.example.hackathonteam4.utils.Constants
import com.example.hackathonteam4.utils.SharedPrefHandler
import com.example.hackathonteam4.R
import com.example.hackathonteam4.domain.model.Lamp
import com.example.hackathonteam4.ui.details.TopAppBar
import kotlin.math.ceil

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController) {
    val viewModel: DetailsViewModel = hiltViewModel()
    val context = LocalContext.current
    val barcode = SharedPrefHandler(context).getString(Constants.BARCODE_VAL)
    val lampState = viewModel.lamp.collectAsState()
    viewModel.fetchLamp(barcode)
    HackathonTeam4Theme {
        Scaffold(topBar = {
            TopAppBar {
                navController.navigateUp()
            }
        }) {
            when (lampState.value) {
                is NetworkResult.Success -> {
                    lampState.value.data?.let { SuccessDetailContent(it) }
                }
                is NetworkResult.Error -> {
                    lampState.value.message?.let { ErrorDetailContent(it) }
                }
                is NetworkResult.Loading -> {
                    LoadingDetailContent()
                }
            }
        }

    }
}

@Composable
fun SuccessDetailContent(lamp: Lamp) {
    Column(
        Modifier
            .padding(MaterialTheme.spacing.large)
            .fillMaxSize()
    ) {
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            repeat(ceil(lamp.rating).toInt()) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_star_24),
                    modifier = Modifier.size(30.dp),
                    contentDescription = "star"
                )
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        ItemCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.small),
            text = stringResource(id = R.string.description) + ": ${lamp.lampDescription}"
        )
        ItemCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.small),
            text = stringResource(id = R.string.model) + ": ${lamp.model}"
        )
        ItemCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.small),
            text = stringResource(id = R.string.brand) + ": ${lamp.brand}"
        )
        ItemCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.small),
            text = stringResource(id = R.string.price) + ": ${lamp.priceInRub}₽   ${lamp.priceInUsd}$"
        )
        ItemCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.small),
            text = stringResource(id = R.string.power) + ": ${lamp.power}"
        )
        ItemCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.small),
            text = stringResource(id = R.string.work_with_backlight_indicator) +
                    ": ${lamp.workWithBacklightIndicator}"
        )
        ItemCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.small),
            text = stringResource(id = R.string.equivalent) + ": ${lamp.equivalent}"
        )
        ItemCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.small),
            text = stringResource(id = R.string.colorTemp) + ": ${lamp.colorTemp}K"
        )
    }
}

@Composable
fun ErrorDetailContent(message: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.fetch_error) + "\n$message",
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LoadingDetailContent() {

}

@Composable
fun ItemCard(text: String, modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = text, modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small))
        Divider(
            modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )
    }

}