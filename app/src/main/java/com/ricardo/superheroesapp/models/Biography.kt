package com.ricardo.superheroesapp.models

import com.google.gson.annotations.SerializedName

open class Biography (

        @SerializedName("full-name")
        val fullName: String,
        @SerializedName("aliases")
        val aliases: Array<String>,
        @SerializedName("place-of-birth")
        val placeOfBirth: String
)