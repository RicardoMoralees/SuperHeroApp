package com.ricardo.superheroesapp.views.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.ricardo.superheroesapp.Constants.Companion.EXTRA_ID_HERO

import com.ricardo.superheroesapp.R
import com.ricardo.superheroesapp.models.Hero
import com.ricardo.superheroesapp.presenters.DataContract
import com.ricardo.superheroesapp.presenters.DetailPresenter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import java.lang.Exception
import android.support.v4.app.NavUtils
import com.ricardo.superheroesapp.models.SearchResult


class DetailActivity : AppCompatActivity(), DataContract.View {

    private lateinit var detailPresenter: DetailPresenter
    var isLoadding: Boolean = false;
    lateinit var hero: Hero

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.extras.getString(EXTRA_ID_HERO, "1")
        detailPresenter = DetailPresenter(this)
        detailPresenter.startHeroDetailService(id)

    }

    override fun onGetDataSuccess(heroes: List<Hero>) {
        this.hero = heroes.get(0)
        runOnUiThread {
            setHeroData()
        }
    }

    override fun onGetDataFailure(message: String) {
    }

    override fun openDetail(hero: Hero) {
        //NOT USED
    }

    fun setHeroData() {
        Picasso.get().load(hero.image.url).into(iv_hero_image, object : Callback {
            override fun onSuccess() {
                load_hero_image.visibility = View.GONE
                iv_hero_image.visibility = View.VISIBLE
            }
            override fun onError(e: Exception?) {

            }
        })
        tv_hero_name_detail.text = hero.name
        tv_hero__full_name.text = hero.biography.fullName
        tv_hero_alias.text = hero.biography.aliases.joinToString()
        tv_hero_lugar_nac.text = hero.biography.placeOfBirth
        tv_hero_genero.text = hero.appearance.gender

        tv_hero_raza.text = hero.appearance.race
        tv_hero_combate.text = hero.powerstats.combat
        tv_hero_durabilidad.text = hero.powerstats.durability
        tv_hero_fuerza.text = hero.powerstats.strength
        tv_hero_intel.text = hero.powerstats.intelligence
        tv_hero_poder.text = hero.powerstats.power
        tv_hero_velocidad.text = hero.powerstats.speed
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onGetSearchSuccess(searchResult: SearchResult) {
        //NOT USED
    }
}
