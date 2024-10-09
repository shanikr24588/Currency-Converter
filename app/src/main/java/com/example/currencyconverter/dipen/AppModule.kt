package com.example.currencyconverter.dipen

import android.app.Application
import androidx.room.Room
import com.example.currencyconverter.data.local.entity.CurrencyRateDataBase
import com.example.currencyconverter.data.remote.CurrencyApi
import com.example.currencyconverter.data.repository.CurrencyRepositoryImpl
import com.example.currencyconverter.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCurrencyApi(): CurrencyApi {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(CurrencyApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(CurrencyApi::class.java)
    }
    @Provides
    @Singleton
    fun provideDatabase(application: Application): CurrencyRateDataBase {
        return Room
            .databaseBuilder(
                application,
                CurrencyRateDataBase::class.java,
                "Currency_db").build()
    }

    @Provides
    @Singleton
    fun provideRepository (api: CurrencyApi, db:CurrencyRateDataBase): CurrencyRepository {
        return CurrencyRepositoryImpl(api = api, dao = db.currencyRateDao)
    }


}