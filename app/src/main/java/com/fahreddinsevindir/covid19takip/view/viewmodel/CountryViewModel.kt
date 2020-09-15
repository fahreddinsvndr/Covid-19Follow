package com.fahreddinsevindir.covid19takip.view.viewmodel


import android.app.Application
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fahreddinsevindir.covid19takip.view.model.CountryDataBase
import com.fahreddinsevindir.covid19takip.view.model.Model
import com.fahreddinsevindir.covid19takip.view.model.Results
import com.fahreddinsevindir.covid19takip.view.service.CountryAPIService
import com.fahreddinsevindir.covid19takip.view.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class CountryViewModel(application: Application) :BaseViewModel(application) {
    private val countryApiService = CountryAPIService()
    private val disposable = CompositeDisposable()
    private var customPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime= 10*60*1000*1000*1000L

    val countries = MutableLiveData<List<Model>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()
    val displayList=MutableLiveData<List<Model>>()

    fun refreshData() {
        val updateTime = customPreferences.getTime()
        if (updateTime != null && updateTime !=0L && System.nanoTime() - updateTime < refreshTime){
            getDataFromSQLite()
        }else{
            getDataFromAPI()
        }

    }
    fun refreshFromAPI(){
        getDataFromAPI()
    }
    private fun getDataFromSQLite(){
        countryLoading.value = true
        launch {
            val countries = CountryDataBase(getApplication()).countryDao().getAllCountries()
            showCountries(countries)
            Toast.makeText(getApplication(),"HAYAT EVE SIĞAR !",Toast.LENGTH_LONG).show()
        }
    }

    private fun getDataFromAPI() {
        countryLoading.value = true
        disposable.add(
            countryApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Results>() {
                    override fun onSuccess(t: Results) {
                        storeInSQLite(t.results)
                        Toast.makeText(getApplication(),"ZORUNDA OLMADIKÇA EVDEN ÇIKMA !",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                        e.printStackTrace()
                    }

                })

        )
    }

    private fun showCountries(countryList: List<Model>) {
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }

    private fun storeInSQLite(list: List<Model>) {
        launch {
            val dao = CountryDataBase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listlong= dao.insertAll(*list.toTypedArray())
            var i =0
            while (i<list.size){
                list[i].uuid = listlong[i].toInt()
                i = i + 1
            }
            showCountries(list)
        }
        customPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

