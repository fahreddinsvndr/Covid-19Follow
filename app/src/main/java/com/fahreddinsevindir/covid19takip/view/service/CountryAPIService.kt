package com.fahreddinsevindir.covid19takip.view.service

import com.fahreddinsevindir.covid19takip.view.model.Results
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryAPIService {

    private val BASE_URL="https://api.collectapi.com"

    private var okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val original = chain.request()

        val requestBuilder = original.newBuilder()
            .addHeader("content-type","application/json")
            .addHeader("authorization", "apikey")
            .method(original.method(), original.body())

        val request = requestBuilder.build()
        chain.proceed(request)

    }.build()

    private val api=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(CountryAPI::class.java)

    fun getData():Single<Results>{
        return api.getCountries()
    }

}
