package com.ricardo.superheroesapp.views.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.ricardo.superheroesapp.R
import com.ricardo.superheroesapp.models.Hero
import com.ricardo.superheroesapp.presenters.DataContract
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class HeroesAdapter(): RecyclerView.Adapter<HeroesViewHolder>() {

    private lateinit var itemClickListener: DataContract.ItemClicListener
    lateinit var heroes: List<Hero>

    constructor(heroes: List<Hero>, itemClicListener: DataContract.ItemClicListener) : this() {
        this.itemClickListener = itemClicListener
        this.heroes = heroes
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hero, parent, false)
        return HeroesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return heroes.size
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val hero = heroes.get(position)
        holder.layout_hero_container.setOnClickListener { _ ->
            itemClickListener.onItemClic(hero)
        }

        return holder.bind(heroes[position])
    }
}

class HeroesViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val ivPhoto: ImageView = itemView.findViewById(R.id.iv_hero_image)
    private val tvName: TextView = itemView.findViewById(R.id.tv_hero_name)
    val layout_hero_container:LinearLayout = itemView.findViewById(R.id.layout_hero_container)
    private val progressLoading: ProgressBar = itemView.findViewById(R.id.load_hero_image)

    fun bind(hero: Hero) {
        Picasso.get().load(hero.image.url).error(R.drawable.hero)
                .into(ivPhoto, object : Callback{
            override fun onSuccess() {
                progressLoading.visibility = View.GONE
                ivPhoto.visibility = View.VISIBLE
            }
            override fun onError(e: Exception?) {
                Log.e("Picasso", e?.message)
            }

        })
        tvName.text = hero.name
    }


}