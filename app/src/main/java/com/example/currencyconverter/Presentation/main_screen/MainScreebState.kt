package com.example.currencyconverter.Presentation.main_screen

import com.example.currencyconverter.domain.model.CurrencyRate

data class MainScreenState(
    val fromCurrencyCode: String = "INR",
    val toCurrencyCode:String = "USD",
    val fromCurrencyValue:String = "0.00",
    val toCurrencyValue:String = "0.00",
    val selection:SelectionState = SelectionState.FROM,
    val currencyRates:Map<String, CurrencyRate> = emptyMap(),
    val error:String? = null
)


enum class SelectionState{
    FROM,
    TO
}