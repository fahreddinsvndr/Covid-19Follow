package com.fahreddinsevindir.covid19takip.view.service

import com.fahreddinsevindir.covid19takip.view.model.Results
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers


interface CountryAPI {

    @Headers("application/json,02Yf5lwKIhyZrhJ5r4xsod:64wTg9U51DnafPItWMluZi")
    @GET("corona/countriesData")
    fun getCountries():Single<Results>
}