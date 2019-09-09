package com.fandango.papayin.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Trailer {

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("iso_639_1")
    @Expose
    var iso: String? = null

    @SerializedName("key")
    @Expose
    var key: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("site")
    @Expose
    var site: String? = null

    @SerializedName("size")
    @Expose
    var size: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

}