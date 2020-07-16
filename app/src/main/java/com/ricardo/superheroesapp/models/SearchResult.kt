package com.ricardo.superheroesapp.models

import com.google.gson.annotations.SerializedName

open class SearchResult (
        @SerializedName("results-for")
        val resultsFor: String,
        @SerializedName("results")
        val results: List<Hero>
)