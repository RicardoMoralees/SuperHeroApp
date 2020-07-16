package com.ricardo.superheroesapp.models.services;

import com.ricardo.superheroesapp.models.Hero
import com.ricardo.superheroesapp.models.SearchResult
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET;
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ServicesInterface {

    @GET("/api/{token}/{id}")
    fun getHero(
            @Path("token") token: String,
            @Path("id") id: Int): Observable<Hero>

    @GET("/api/{token}/search/{hero}")
    fun searchHero(
            @Path("token") token: String,
            @Path("hero") hero: String
    ): Call<SearchResult>
}
