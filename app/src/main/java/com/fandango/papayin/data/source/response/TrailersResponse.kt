package com.fandango.papayin.data.source.response

import com.fandango.papayin.data.model.Trailer
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TrailersResponse {

    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("results")
    @Expose
    var trailers: MutableList<Trailer>? = null

}