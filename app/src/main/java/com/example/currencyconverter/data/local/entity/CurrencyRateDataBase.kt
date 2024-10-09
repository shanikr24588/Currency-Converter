package com.example.currencyconverter.data.local.entity

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyRateEntity::class], version = 1)

abstract class CurrencyRateDataBase:RoomDatabase() {

//    Use the currencyRateDao property to get an instance of the DAO.
    abstract val currencyRateDao: CurrencyRateDao
}