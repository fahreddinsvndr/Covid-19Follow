package com.fahreddinsevindir.covid19takip.view.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CountryDao {
    @Insert
    suspend fun insertAll(vararg countries:Model) : List<Long>
    @Query("SELECT * FROM model")
    suspend fun getAllCountries():List<Model>
    @Query("SELECT * FROM model WHERE uuid = :countryId")
    suspend fun getCountry(countryId: Int) : Model
    @Query("DELETE FROM model")
    suspend fun deleteAllCountries()
}