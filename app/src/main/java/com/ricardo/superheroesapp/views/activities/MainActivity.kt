package com.ricardo.superheroesapp.views.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.ricardo.superheroesapp.Constants.Companion.EXTRA_ID_HERO
import com.ricardo.superheroesapp.R
import com.ricardo.superheroesapp.models.Hero
import com.ricardo.superheroesapp.models.SearchResult
import com.ricardo.superheroesapp.presenters.DataContract
import com.ricardo.superheroesapp.presenters.MainPresenter
import com.ricardo.superheroesapp.views.adapters.HeroesAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DataContract.View {

    private lateinit var mainPresenter: MainPresenter
    private lateinit var recyclerHeroes: RecyclerView
    var isLoadding: Boolean = false;
    var isSearching: Boolean = false;
    var heroes: List<Hero> = ArrayList<Hero>()
    lateinit var adapter: HeroesAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenter(this);
        setRecycler()
        setSearchView()
        if (heroes.isEmpty()) {
            mainPresenter.startGetHeroesService();
            load_heroes.visibility = View.VISIBLE
        }
        recyclerHeroes.requestFocus()
    }

    fun setRecycler() {
        recyclerHeroes = rv_home_heroes
        linearLayoutManager = LinearLayoutManager(this)
        recyclerHeroes.layoutManager = linearLayoutManager
        adapter = HeroesAdapter(heroes, mainPresenter)
        recyclerHeroes.adapter = adapter

        rv_home_heroes.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && !isLoadding && !isSearching) {
                    isLoadding = true
                    load_heroes.visibility = View.VISIBLE
                    mainPresenter.startGetHeroesService()
                }
            }
        })
    }

    fun setSearchView() {
        search_home.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                Log.e("Search", p0)
                if (p0 != null) {
                        mainPresenter.startSearchHeroService(p0!!)
                        hideKeyboard()
                        isSearching = true
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }

    override fun onGetDataSuccess(heroes: List<Hero>) {
        this.heroes = this.heroes + heroes
        adapter.heroes = this.heroes
        runOnUiThread {
            adapter.notifyDataSetChanged()
            recyclerHeroes.requestLayout()
        }
        isLoadding = false
        load_heroes.visibility = View.GONE
    }

    override fun onGetSearchSuccess(searchResult: SearchResult) {
        adapter.heroes = searchResult.results
        runOnUiThread {
            adapter.notifyDataSetChanged()
        }
        isLoadding = false
        load_heroes.visibility = View.GONE
    }

    override fun onGetDataFailure(message: String) {
        runOnUiThread {
            //Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            isLoadding = false
            load_heroes.visibility = View.GONE

            recyclerHeroes.invalidate()
            recyclerHeroes.requestLayout()
            main_container.invalidate()
            main_container.requestLayout()
        }
    }

    override fun openDetail(hero: Hero) {
        val intent: Intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(EXTRA_ID_HERO, hero.id)
        }
        startActivity(intent)
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onResume() {
        super.onResume()
        linearLayoutManager.scrollToPosition(0)
    }

    override fun onBackPressed() {
        if (isSearching){
            onGetDataSuccess(heroes)
            isSearching = false
        } else {
            super.onBackPressed()
        }
    }
}
