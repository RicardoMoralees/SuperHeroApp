package com.ricardo.superheroesapp.presenters

import com.ricardo.superheroesapp.models.Hero
import com.ricardo.superheroesapp.models.SearchResult
import com.ricardo.superheroesapp.models.services.HeroServices
import com.ricardo.superheroesapp.views.adapters.HeroesAdapter
import kotlin.random.Random

open class MainPresenter(private var mGetDataView: DataContract.View?)
    : DataContract.Presenter,
        DataContract.GetDataListener,
        DataContract.ItemClicListener{

    private var services = HeroServices(this)

    override fun onSuccess(heroes: List<Hero>) {
        mGetDataView?.onGetDataSuccess(heroes)
    }

    override fun onSuccessSearch(searchResult: SearchResult) {
        mGetDataView?.onGetSearchSuccess(searchResult)
    }

    override fun onFailure(message: String) {
        mGetDataView?.onGetDataFailure(message)
    }

    override fun startGetHeroesService() {
        var numbers: List<Int> = ArrayList<Int>()
        val randomValues = List(5) { Random.nextInt(1, 731) }
        numbers += randomValues
        numbers.distinct()
        services.getHeroes(numbers)
    }

    override fun startSearchHeroService(query: String) {
        services.searchHeroe(query)
    }

    override fun onItemClic(hero: Hero) {
        mGetDataView?.openDetail(hero)
    }
}