package com.ricardo.superheroesapp.models

import com.google.gson.annotations.SerializedName

open class Hero (

        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("image")
        val image: Image,
        @SerializedName("powerstats")
        val powerstats: Powerstats,
        @SerializedName("biography")
        val biography: Biography,
        @SerializedName("appearance")
        val appearance: Appearance
)