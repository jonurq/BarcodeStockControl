package com.jonurq.barcodestock.ui.quantity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonurq.barcodestock.data.StockItem
import com.jonurq.barcodestock.data.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class QuantityUiState(
    val currentInput: String = "",
    val existingQuantity: Int? = null,
    val isSaved: Boolean = false
)

@HiltViewModel
class QuantityViewModel @Inject constructor(
    private val repository: StockRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(QuantityUiState())
    val uiState: StateFlow<QuantityUiState> = _uiState.asStateFlow()

    fun checkExisting(barcode: String) {
        viewModelScope.launch {
            val allItems = repository.getAllItems().first()
            val existing = allItems.find { it.barcode == barcode }
            _uiState.update { it.copy(existingQuantity = existing?.quantity) }
        }
    }

    fun appendInput(digit: String) {
        _uiState.update { 
            // Prevent leading zeros if not decimal, or prevent extremely long numbers
            if (it.currentInput.length < 5) {
                it.copy(currentInput = it.currentInput + digit)
            } else {
                it
            }
        }
    }

    fun clearInput() {
        _uiState.update { it.copy(currentInput = "") }
    }

    fun saveQuantity(barcode: String) {
        val qty = _uiState.value.currentInput.toIntOrNull()
        if (qty != null && qty > 0) {
            viewModelScope.launch {
                repository.insertOrUpdateItem(
                    StockItem(
                        barcode = barcode,
                        quantity = qty,
                        timestamp = System.currentTimeMillis()
                    )
                )
                _uiState.update { it.copy(isSaved = true) }
            }
        }
    }
}
