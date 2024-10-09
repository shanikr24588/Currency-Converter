package com.example.currencyconverter.data.remote

import com.example.currencyconverter.data.remote.Dto.CurrencyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
@GET("v1/latest")
suspend fun getLatestRate(
    @Query("apikey")apiKey:String = API_KEY
): CurrencyDto
companion object{
    const val API_KEY= "fca_live_s2h8SOcdy2YVJ6yiN9zYgpyt630odQx6BmO5t2mJ"
    const val BASE_URL ="https://api.freecurrencyapi.com/"
}
}