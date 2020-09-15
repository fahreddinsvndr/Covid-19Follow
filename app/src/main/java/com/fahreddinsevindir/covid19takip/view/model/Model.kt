package com.fahreddinsevindir.covid19takip.view.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Model(
    @ColumnInfo(name = "country")
    @SerializedName("country")
    val countryName:String?,
    @ColumnInfo(name = "totalCases")
    @SerializedName("totalCases")
    val tplmVaka:String?,
    @ColumnInfo(name = "newCases")
    @SerializedName("newCases")
    val yeniVaka:String?,
    @ColumnInfo(name = "totalDeaths")
    @SerializedName("totalDeaths")
    val tplmOlum:String?,
    @ColumnInfo(name = "newDeaths")
    @SerializedName("newDeaths")
    val yeniOlum:String?,
    @ColumnInfo(name = "totalRecovered")
    @SerializedName("totalRecovered")
    val tplmİyilesen:String?,
    @ColumnInfo(name = "activeCases")
    @SerializedName("activeCases")
    val aktifVaka:String?){

    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}