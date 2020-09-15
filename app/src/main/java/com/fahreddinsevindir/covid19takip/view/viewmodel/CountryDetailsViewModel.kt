package com.fahreddinsevindir.covid19takip.view.viewmodel


import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fahreddinsevindir.covid19takip.view.model.CountryDataBase
import com.fahreddinsevindir.covid19takip.view.model.Model
import kotlinx.coroutines.launch
import java.util.*

class CountryDetailsViewModel(application: Application): BaseViewModel(application) {
    val countryLiveData= MutableLiveData<Model>()

    fun getDataFromRoom(uuid: Int){
        launch {
            val dao = CountryDataBase(getApplication()).countryDao()
            val country = dao.getCountry(uuid)
            countryLiveData.value = country
        }
    }
}