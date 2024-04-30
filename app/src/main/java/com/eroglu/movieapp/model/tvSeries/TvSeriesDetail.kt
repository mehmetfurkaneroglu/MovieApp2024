package com.eroglu.movieapp.model.tvSeries


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TvSeriesDetail(
    @SerializedName("adult")
    val adult: Boolean? = false,
    @SerializedName("backdrop_path")
    val backdropPath: String? = "",
    @SerializedName("created_by")
    val createdBy: List<CreatedBy>? = listOf(),
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>? = listOf(),
    @SerializedName("first_air_date")
    val firstAirDate: String? = "",
    @SerializedName("genres")
    val genres: List<Genre>? = listOf(),
    @SerializedName("homepage")
    val homepage: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("in_production")
    val inProduction: Boolean? = false,
    @SerializedName("languages")
    val languages: List<String>? = listOf(),
    @SerializedName("last_air_date")
    val lastAirDate: String? = "",
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir? = LastEpisodeToAir(),
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("networks")
    val networks: List<NetworkX>? = listOf(),
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: NextEpisodeToAir? = NextEpisodeToAir(),
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int? = 0,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int? = 0,
    @SerializedName("origin_country")
    val originCountry: List<String>? = listOf(),
    @SerializedName("original_language")
    val originalLanguage: String? = "",
    @SerializedName("original_name")
    val originalName: String? = "",
    @SerializedName("overview")
    val overview: String? = "",
    @SerializedName("popularity")
    val popularity: Double? = 0.0,
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>? = listOf(),
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>? = listOf(),
    @SerializedName("seasons")
    val seasons: List<SeasonX>? = listOf(),
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>? = listOf(),
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("tagline")
    val tagline: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("vote_average")
    val voteAverage: Double? = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int? = 0
): Serializable