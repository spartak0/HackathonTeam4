package com.example.hackathonteam4.ui.handle_input_barcode

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.hackathonteam4.R
import com.example.hackathonteam4.ui.details.TopAppBar
import com.example.hackathonteam4.ui.navigation.Screen
import com.example.hackathonteam4.ui.theme.spacing
import com.example.hackathonteam4.utils.Constants
import com.example.hackathonteam4.utils.SharedPrefHandler

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HandleInputBarcode(navController: NavController) {
    var barcodeText by remember { mutableStateOf("") }
    val context = LocalContext.current
    val enabled = barcodeText.length == Constants.MAX_LENGTH_BARCODE
    val backgroundColorBtn by animateColorAsState(
        if (enabled) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
    )
    Scaffold(topBar = {
        TopAppBar {
            navController.navigateUp()
        }
    }) {
        Box(
            Modifier
                .padding(MaterialTheme.spacing.medium)
                .fillMaxSize(),
            Alignment.Center
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
                        if (barcodeText.isDigitsOnly()) {
                            if (enabled) {
                                SharedPrefHandler(context).putString(
                                    Constants.BARCODE_VAL,
                                    barcodeText
                                )
                                navController.navigate(
                                    Screen.DetailScreen.route
                                )
                            }
                        } else Toast.makeText(context, context.getText(R.string.only_digits),Toast.LENGTH_LONG).show()
                    },
                    modifier = Modifier.clip(CircleShape),
                    enabled = enabled
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                            .size(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                            contentDescription = "forward_arrow",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}