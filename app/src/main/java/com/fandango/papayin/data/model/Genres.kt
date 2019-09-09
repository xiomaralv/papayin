package com.fandango.papayin.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Genres{
    @SerializedName("name")
    @Expose
    var name: String? = null
}