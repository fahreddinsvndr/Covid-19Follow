package com.fahreddinsevindir.covid19takip.view.model

import com.google.gson.annotations.SerializedName


data class Results (
    @SerializedName("result")
    var results: List<Model>
)