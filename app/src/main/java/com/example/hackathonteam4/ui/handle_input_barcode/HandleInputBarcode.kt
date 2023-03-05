package com.example.hackathonteam4.ui.handle_input_barcode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hackathonteam4.R
import com.example.hackathonteam4.ui.navigation.Screen
import com.example.hackathonteam4.ui.theme.spacing
import com.example.hackathonteam4.utils.Constants

@Composable
fun HandleInputBarcode(navController: NavController) {
    var barcodeText by remember { mutableStateOf("") }
    Column(
        Modifier
            .padding(MaterialTheme.spacing.medium)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.medium)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = barcodeText,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = {
                    if (it.length <= Constants.MAX_LENGTH_BARCODE) barcodeText = it
                },
                placeholder = { Text(stringResource(R.string.tf_barcode_input)) },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(10.dp))
            IconButton(
                onClick = {
                    if (barcodeText.length <= Constants.MAX_LENGTH_BARCODE) navController.navigate(
                        Screen.DetailScreen.route
                    )
                },
                modifier = Modifier.clip(CircleShape),
                enabled = barcodeText.length == 13
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                    contentDescription = "forward_arrow",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            color = MaterialTheme.colorScheme.primary
                        ),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

        }
    }
}