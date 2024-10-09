package com.example.currencyconverter.data.repository

import com.example.currencyconverter.data.local.entity.CurrencyRateDao
import com.example.currencyconverter.data.local.entity.toCurrencyRate
import com.example.currencyconverter.data.local.entity.toCurrencyRateEntity
import com.example.currencyconverter.data.remote.CurrencyApi
import com.example.currencyconverter.data.remote.Dto.toCurrencyRates
import com.example.currencyconverter.domain.model.CurrencyRate
import com.example.currencyconverter.domain.model.Resource
import com.example.currencyconverter.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class CurrencyRepositoryImpl (
    private val api: CurrencyApi,
    private val dao: CurrencyRateDao
):CurrencyRepository{
    override fun getCurrencyRateList(): Flow<Resource<List<CurrencyRate>>> = flow {
          val localCurrencyRates = getLocalCurrencyRates()
        emit(Resource.Success(localCurrencyRates))
        try {
            val newRates = getRemoteCurrencyRate()
            updateLocalCurrencyRates(newRates)
            emit(Resource.Success(newRates))

        } catch (e: IOException){
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection",
                data = localCurrencyRates
            ))
        }
        catch (e: Exception){
            emit(Resource.Error(
                message = " Oops, something went wrong! ${e.message}",
                data = localCurrencyRates
            ))
        }


    }
    private suspend fun getLocalCurrencyRates(): List<CurrencyRate>{
        return dao.getAllCurrencyRates().map { it.toCurrencyRate() }
    }
    private suspend fun getRemoteCurrencyRate():List<CurrencyRate> {
        val response = api.getLatestRate()
        return response.toCurrencyRates()
    }

    private suspend fun updateLocalCurrencyRates(currencyRates:List<CurrencyRate>){
        dao.upsertAll(currencyRates.map { it.toCurrencyRateEntity() })
    }

}
