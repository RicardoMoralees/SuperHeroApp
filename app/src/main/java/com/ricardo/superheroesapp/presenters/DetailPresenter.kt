package com.ricardo.superheroesapp.presenters

import android.util.Log
import com.ricardo.superheroesapp.models.Hero
import com.ricardo.superheroesapp.models.SearchResult
import com.ricardo.superheroesapp.models.services.HeroServices
import kotlin.random.Random

open class DetailPresenter(private var mGetDataView: DataContract.View?)
    : DataContract.DetailPresenter, DataContract.GetDataListener {

    private var services = HeroServices(this)

    override fun onSuccess(heroes: List<Hero>) {
        mGetDataView?.onGetDataSuccess(heroes)
    }

    override fun onFailure(message: String) {
        mGetDataView?.onGetDataFailure(message)
    }

    override fun startHeroDetailService(id: String) {
        val idHero: Int = id.toInt()
        val numbers: List<Int> = arrayListOf(idHero)
        Log.w("Numbers size", numbers.size.toString())
        services.getHeroes(numbers)
    }
    override fun onSuccessSearch(searchResult: SearchResult) {
        //Not Used
    }
}