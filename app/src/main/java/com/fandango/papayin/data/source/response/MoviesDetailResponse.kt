package com.fandango.papayin.data.source.response

import com.fandango.papayin.data.model.Genres
import com.fandango.papayin.data.model.Movie
import com.fandango.papayin.data.model.ProductionCompanies
import com.fandango.papayin.data.model.ProductionCountries
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class MoviesDetailResponse {


    @SerializedName("id")
    @Expose
    var id: Int = 0


    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null


    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    @SerializedName("genres")
    @Expose
    var genres: List<Genres>? = null


    @SerializedName("production_companies")
    @Expose
    var productionCompanies: MutableList<ProductionCompanies>? = null


    @SerializedName("production_countries")
    @Expose
    var productionCountries: List<ProductionCountries>? = null


}