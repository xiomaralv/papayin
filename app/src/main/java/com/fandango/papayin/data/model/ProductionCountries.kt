package com.fandango.papayin.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class  ProductionCountries{
    @SerializedName("name")
    @Expose
    var name: String? = null
}