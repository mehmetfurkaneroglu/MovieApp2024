package com.eroglu.movieapp.model.moviesOrTvSeries


import com.eroglu.movieapp.model.tvSeries.Network
import com.eroglu.movieapp.model.tvSeries.Season
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MoviesorTvSeriesDetails(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("created_by")
    val createdBy: List<Any?>? = null,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int?>? = null,
    @SerializedName("runtime")
    val runtime: Int? = null,
    @SerializedName("revenue")
    val revenue: Int? = null,
    @SerializedName("budget")
    val budget: Int? = null,
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @SerializedName("genres")
    val genres: List<Any?>? = null,
    @SerializedName("homepage")
    val homepage: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("in_production")
    val inProduction: Boolean? = null,
    @SerializedName("languages")
    val languages: List<Any?>? = null,
    @SerializedName("last_air_date")
    val lastAirDate: Any? = null,
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: Any? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("networks")
    val networks: List<Network?>? = null,
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: Any? = null,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int? = null,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int? = null,
    @SerializedName("origin_country")
    val originCountry: List<String?>? = null,
    @SerializedName("original_name")
    val originalName: String? = null,
    @SerializedName("production_companies")
    val productionCompanies: List<Any?>? = null,
    @SerializedName("production_countries")
    val productionCountries: List<Any?>? = null,
    @SerializedName("seasons")
    val seasons: List<Season?>? = null,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<Any?>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int?>? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
):Serializable