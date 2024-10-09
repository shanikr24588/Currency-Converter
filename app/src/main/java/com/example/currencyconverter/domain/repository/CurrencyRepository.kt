package com.example.currencyconverter.domain.repository

import com.example.currencyconverter.domain.model.CurrencyRate
import com.example.currencyconverter.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getCurrencyRateList():Flow<Resource<List<CurrencyRate>>>
}