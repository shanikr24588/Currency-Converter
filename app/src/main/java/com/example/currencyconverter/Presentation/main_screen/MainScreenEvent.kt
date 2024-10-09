package com.example.currencyconverter.Presentation.main_screen

sealed class MainScreenEvent {
    object FromCurrencySelect:MainScreenEvent()
    object ToCurrencySelect:MainScreenEvent()
    object SwapIconClicked:MainScreenEvent()
    data class BottomSheetItemClicked(val value:String):MainScreenEvent()
    data class NumberButtonClicked(val value:String):MainScreenEvent()
}