package com.example.currencyconverter.data.local.entity

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CurrencyRateDao {
    @Upsert
    suspend fun upsertAll(currencyRates:List<CurrencyRateEntity>)
    @Query("SELECT * FROM currencyrateentity")
    suspend fun getAllCurrencyRates():List<CurrencyRateEntity>

}