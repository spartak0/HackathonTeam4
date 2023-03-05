package com.example.hackathonteam4.ui.scanner_screen

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hackathonteam4.ui.details.TopAppBar
import com.example.hackathonteam4.ui.navigation.Screen
import com.example.hackathonteam4.ui.navigation.navigate
import com.example.hackathonteam4.utils.Constants
import com.example.hackathonteam4.utils.SharedPrefHandler
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScannerScreen(navController: NavController) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var previewBoth by remember {
        mutableStateOf<Preview?>(null)
    }
    Scaffold(topBar = {
        TopAppBar {
            navController.navigateUp()
        }
    }) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                AndroidView(
                    factory = { context ->
                        PreviewView(context).apply {
                            this.scaleType = PreviewView.ScaleType.FILL_CENTER
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                        }
                    },
                    modifier = Modifier.fillMaxSize(),
                    update = { previewView ->
                        val cameraSelector: CameraSelector = CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build()
                        val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()
                        val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> =
                            ProcessCameraProvider.getInstance(context)

                        cameraProviderFuture.addListener(
                            {
                                previewBoth = Preview.Builder().build().also {
                                    it.setSurfaceProvider(previewView.surfaceProvider)
                                }
                                val cameraProvider: ProcessCameraProvider =
                                    cameraProviderFuture.get()
                                val barcodeAnalyzer = CodeAnalyzer { barcodes ->
                                    barcodes.forEach {
                                        it.rawValue?.let { barcodeValue ->
                                            SharedPrefHandler(context).putString(
                                                Constants.BARCODE_VAL,
                                                barcodeValue
                                            )
                                            navController.navigate(
                                                Screen.DetailScreen.route
                                            )
                                        }
                                    }

                                }
                                val imageAnalysis: ImageAnalysis = ImageAnalysis.Builder()
                                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                    .build()
                                    .also {
                                        it.setAnalyzer(cameraExecutor, barcodeAnalyzer)
                                    }

                                try {
                                    cameraProvider.unbindAll()
                                    cameraProvider.bindToLifecycle(
                                        lifecycleOwner,
                                        cameraSelector,
                                        previewBoth,
                                        imageAnalysis
                                    )
                                } catch (e: Exception) {
                                    Log.d("TAG", "CameraPreview: ${e.localizedMessage}")
                                }
                            }, ContextCompat.getMainExecutor(context)
                        )
                    }
                )
                Box(
                    modifier = Modifier
                        .border(1.dp, MaterialTheme.colorScheme.primary, RectangleShape)
                        .size(
                            if (LocalConfiguration.current.screenWidthDp < LocalConfiguration.current.screenHeightDp) {
                                LocalConfiguration.current.screenWidthDp.times(0.7).dp
                            } else {
                                LocalConfiguration.current.screenHeightDp.times(0.7).dp
                            }
                        )
                        .background(Color.Transparent)
                ) {}
            }
        }
    }

}