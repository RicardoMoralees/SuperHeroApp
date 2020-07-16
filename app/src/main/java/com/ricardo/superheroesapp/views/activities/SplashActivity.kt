package com.ricardo.superheroesapp.views.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView

import com.ricardo.superheroesapp.R
import com.ricardo.superheroesapp.models.services.HeroServices
import io.reactivex.disposables.Disposable
import kotlin.random.Random

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent: Intent = Intent(this,MainActivity::class.java)
        intent.flags.and(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        Handler().postDelayed(
                {
                    startActivity(intent)
                    finish()
                },
                3000 // value in milliseconds
        )
    }
}
