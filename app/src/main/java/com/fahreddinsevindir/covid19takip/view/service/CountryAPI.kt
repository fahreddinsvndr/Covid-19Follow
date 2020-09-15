package com.fahreddinsevindir.covid19takip.view.service

import com.fahreddinsevindir.covid19takip.view.model.Results
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers


interface CountryAPI {

    @Headers("application/json,your token apikey")
    @GET("corona/countriesData")
    fun getCountries():Single<Results>
}
