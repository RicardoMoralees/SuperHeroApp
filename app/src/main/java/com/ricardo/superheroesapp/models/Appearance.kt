package com.ricardo.superheroesapp.models

import com.google.gson.annotations.SerializedName

open class Appearance (

        @SerializedName("gender")
        val gender: String,
        @SerializedName("race")
        val race: String
)