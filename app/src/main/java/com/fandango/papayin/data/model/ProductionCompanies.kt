package com.fandango.papayin.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductionCompanies{

    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("logo_path")
    @Expose
    var logoPath: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("origin_country")
    @Expose
    var originCountry: String? = null

}