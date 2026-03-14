package com.jonurq.barcodestock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jonurq.barcodestock.ui.main.MainScreen
import com.jonurq.barcodestock.ui.quantity.QuantityScreen
import com.jonurq.barcodestock.ui.scanner.CameraPreviewScreen
import com.jonurq.barcodestock.ui.theme.BarcodeStockControlTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BarcodeStockControlTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") {
                            MainScreen(
                                onNavigateToScanner = { navController.navigate("scanner") },
                                onNavigateToEdit = { barcode -> navController.navigate("quantity/$barcode") }
                            )
                        }
                        composable("scanner") {
                            CameraPreviewScreen(
                                onBarcodeDetected = { barcode ->
                                    navController.navigate("quantity/$barcode") {
                                        popUpTo("main")
                                    }
                                }
                            )
                        }
                        composable("quantity/{barcode}") { backStackEntry ->
                            val barcode = backStackEntry.arguments?.getString("barcode") ?: ""
                            QuantityScreen(
                                barcode = barcode,
                                onSaveSuccess = { 
                                    navController.navigate("main") {
                                        popUpTo("main") { inclusive = true }
                                    }
                                },
                                onCancel = { navController.navigateUp() }
                            )
                        }
                    }
                }
            }
        }
    }
}