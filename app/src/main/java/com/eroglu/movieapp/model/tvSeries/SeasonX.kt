package com.eroglu.movieapp.model.tvSeries


import com.google.gson.annotations.SerializedName

data class SeasonX(
    @SerializedName("air_date")
    val airDate: String? = null,
    @SerializedName("episode_count")
    val episodeCount: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("season_number")
    val seasonNumber: Int? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null
)