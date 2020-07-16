package com.ricardo.superheroesapp.presenters

import com.ricardo.superheroesapp.models.Hero
import com.ricardo.superheroesapp.models.SearchResult
import com.ricardo.superheroesapp.views.adapters.HeroesAdapter

interface DataContract {

    interface View {
        fun onGetDataSuccess(heroes: List<Hero>)
        fun onGetSearchSuccess(searchResult: SearchResult)
        fun onGetDataFailure(message: String)
        fun openDetail(hero: Hero)
    }

    interface Presenter {
        fun startGetHeroesService()
        fun startSearchHeroService(query: String)
    }

    interface DetailPresenter {
        fun startHeroDetailService(id: String)
    }

    interface GetDataListener {
        fun onSuccess(heroes: List<Hero>)
        fun onSuccessSearch(searchResult: SearchResult)
        fun onFailure(message: String)
    }

    interface ItemClicListener {
        fun onItemClic(hero: Hero)
    }
}