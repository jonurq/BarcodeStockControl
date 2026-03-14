package com.jonurq.barcodestock.ui.quantity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun QuantityScreen(
    barcode: String,
    onSaveSuccess: () -> Unit,
    onCancel: () -> Unit,
    viewModel: QuantityViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(barcode) {
        viewModel.checkExisting(barcode)
    }
    
    // Auto-navigate back on success
    LaunchedEffect(uiState.isSaved) {
        if (uiState.isSaved) {
            onSaveSuccess()
        }
    }

    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { Text("Stock for: $barcode") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (uiState.existingQuantity != null) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "This item was already scanned with quantity: ${uiState.existingQuantity}. Selecting a new quantity will overwrite the previous one.",
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            Text(
                text = uiState.currentInput.ifEmpty { "0" },
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(32.dp)
            )

            // Number Pad Grid
            val buttons = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "C", "0", "OK")
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(buttons) { btn ->
                    Button(
                        onClick = {
                            when (btn) {
                                "C" -> viewModel.clearInput()
                                "OK" -> viewModel.saveQuantity(barcode)
                                else -> viewModel.appendInput(btn)
                            }
                        },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize(),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(
                            text = btn,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancel")
            }
        }
    }
}
