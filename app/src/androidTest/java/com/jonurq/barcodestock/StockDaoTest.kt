package com.jonurq.barcodestock

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jonurq.barcodestock.data.StockDao
import com.jonurq.barcodestock.data.StockDatabase
import com.jonurq.barcodestock.data.StockItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class StockDaoTest {
    private lateinit var db: StockDatabase
    private lateinit var dao: StockDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, StockDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.stockDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadStockItem() = runBlocking {
        val item = StockItem(barcode = "12345", quantity = 10, timestamp = System.currentTimeMillis())
        dao.insertOrUpdate(item)
        val allItems = dao.getAllItems().first()
        assertEquals(1, allItems.size)
        assertEquals("12345", allItems[0].barcode)
        assertEquals(10, allItems[0].quantity)
    }

    @Test
    @Throws(Exception::class)
    fun updateExistingStockItem() = runBlocking {
        val item1 = StockItem(barcode = "12345", quantity = 10, timestamp = System.currentTimeMillis())
        dao.insertOrUpdate(item1)
        
        val item2 = StockItem(barcode = "12345", quantity = 25, timestamp = System.currentTimeMillis() + 1000)
        dao.insertOrUpdate(item2)

        val allItems = dao.getAllItems().first()
        assertEquals(1, allItems.size)
        assertEquals(25, allItems[0].quantity)
    }

    @Test
    @Throws(Exception::class)
    fun deleteStockItem() = runBlocking {
        val item = StockItem(barcode = "12345", quantity = 10, timestamp = System.currentTimeMillis())
        dao.insertOrUpdate(item)
        dao.deleteItem(item)
        val allItems = dao.getAllItems().first()
        assertEquals(0, allItems.size)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAllItems() = runBlocking {
        dao.insertOrUpdate(StockItem(barcode = "1", quantity = 1, timestamp = 1L))
        dao.insertOrUpdate(StockItem(barcode = "2", quantity = 2, timestamp = 2L))
        dao.deleteAll()
        val allItems = dao.getAllItems().first()
        assertEquals(0, allItems.size)
    }
}
