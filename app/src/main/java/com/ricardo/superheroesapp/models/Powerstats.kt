package com.ricardo.superheroesapp.models

import com.google.gson.annotations.SerializedName

open class Powerstats (

        @SerializedName("intelligence")
        val intelligence: String,
        @SerializedName("strength")
        val strength: String,
        @SerializedName("speed")
        val speed: String,
        @SerializedName("durability")
        val durability: String,
        @SerializedName("power")
        val power: String,
        @SerializedName("combat")
        val combat: String
)