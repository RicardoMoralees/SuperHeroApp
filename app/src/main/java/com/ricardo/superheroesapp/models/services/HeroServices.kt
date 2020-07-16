package com.ricardo.superheroesapp.models.services

import android.util.Log
import android.widget.Toast
import com.ricardo.superheroesapp.Constants.Companion.ACCESS_TOKEN
import com.ricardo.superheroesapp.models.Hero
import com.ricardo.superheroesapp.models.SearchResult
import com.ricardo.superheroesapp.presenters.DataContract
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


open class HeroServices(var callback: DataContract.GetDataListener)
    : HeroesServiceInterface {

    var heroes: List<Hero> = ArrayList<Hero>()

    override fun getHeroes(heroesId: List<Int>) {
        val request = ServiceBuilder.buildService(ServicesInterface::class.java)
        val requests = ArrayList<Observable<Hero>>()

        heroesId.forEach { id ->
            requests.add(request.getHero(ACCESS_TOKEN, id))
        }
        Log.e("Requests: ", requests.size.toString())
        Observable
                .zip(requests)
                         {
                            heroes ->
                             val result = ArrayList<Hero>()
                             for (o in heroes) {
                                 val c = o as Hero
                                 // additional code here, I am just concatenating them together
                                 // This gives me a list with the individual result of each Observable (for instance an API call)
                                 result.add(c)
                             }
                             getRequestHeroes(result)
                        }
                // Will be triggered if all requests will end successfully (4xx and 5xx also are successful requests too)
                .subscribe({
                    it ->
                    Log.e("HeroService", "Sucess!!!!")
                    callback.onSuccess(heroes)
                }) { it ->
                    //Do something on error completion of requests
                    Log.e("Fail", it.message)
                    callback.onFailure(it.message.orEmpty())
                }
    }

    fun getRequestHeroes (heroes:List<Hero>) {
        this.heroes = heroes
    }

    override fun searchHeroe(hero: String) {
        val request = ServiceBuilder.buildService(ServicesInterface::class.java)
        val call = request.searchHero(ACCESS_TOKEN,hero)

        call.enqueue(object : Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                if (response.isSuccessful && response.body() != null){
                    callback.onSuccessSearch(response.body()!!)
                } else {
                    callback.onFailure(response.message())
                }
            }
            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                callback.onFailure(t.message.orEmpty())
            }
        })
    }

}