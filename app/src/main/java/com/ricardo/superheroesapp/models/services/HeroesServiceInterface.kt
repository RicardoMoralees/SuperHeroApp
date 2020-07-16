package com.ricardo.superheroesapp.models.services

import com.ricardo.superheroesapp.models.Hero

interface HeroesServiceInterface {
    fun getHeroes(heroes: List<Int>)
    fun searchHeroe(hero: String)
}