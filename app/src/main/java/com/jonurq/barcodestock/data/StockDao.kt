package com.jonurq.barcodestock.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDao {
    @Query("SELECT * FROM stock_items ORDER BY timestamp DESC")
    fun getAllItems(): Flow<List<StockItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(item: StockItem)

    @Delete
    suspend fun deleteItem(item: StockItem)

    @Query("DELETE FROM stock_items")
    suspend fun deleteAll()
}
