package com.jonurq.barcodestock.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_items")
data class StockItem(
    @PrimaryKey
    val barcode: String,
    val quantity: Int,
    val timestamp: Long
)
