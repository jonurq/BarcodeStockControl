package com.jonurq.barcodestock.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StockRepository @Inject constructor(
    private val stockDao: StockDao
) {
    fun getAllItems(): Flow<List<StockItem>> = stockDao.getAllItems()

    suspend fun insertOrUpdateItem(item: StockItem) {
        stockDao.insertOrUpdate(item)
    }

    suspend fun deleteItem(item: StockItem) {
        stockDao.deleteItem(item)
    }

    suspend fun deleteAllItems() {
        stockDao.deleteAll()
    }
}
