package com.marcosholgado.forex.network

import com.google.gson.internal.LinkedTreeMap
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ForexService {

    @GET("latest?access_key=55b3d7ea41ece863950d0174813e0195")
    fun getCurrencies(@Query("base") base: String, @Query("symbols") symbols: String): Flowable<CurrenciesResponse>


    @GET("{date}?access_key=55b3d7ea41ece863950d0174813e0195")
    fun compareCurrencies(@Path("date") date: String, @Query("base") base: String, @Query("symbols") symbols: String): Flowable<CurrenciesDateResponse>

    data class CurrenciesResponse(
        val success: Boolean,
        val base: String?,
        val rates: LinkedTreeMap<String, String>?,
        val error: CurrencyError?
    )

    data class CurrenciesDateResponse(
        val success: Boolean,
        val base: String?,
        val date: String?,
        val rates: LinkedTreeMap<String, String>?,
        val error: CurrencyError?
    )

    data class CurrencyError(val info: String)

}