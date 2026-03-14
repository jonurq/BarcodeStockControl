package com.jonurq.barcodestock.di

import android.content.Context
import androidx.room.Room
import com.jonurq.barcodestock.data.StockDao
import com.jonurq.barcodestock.data.StockDatabase
import com.jonurq.barcodestock.data.StockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideStockDatabase(@ApplicationContext context: Context): StockDatabase {
        return Room.databaseBuilder(
            context,
            StockDatabase::class.java,
            "stock_db"
        ).build()
    }

    @Provides
    fun provideStockDao(database: StockDatabase): StockDao {
        return database.stockDao()
    }

    @Provides
    @Singleton
    fun provideStockRepository(stockDao: StockDao): StockRepository {
        return StockRepository(stockDao)
    }
}
