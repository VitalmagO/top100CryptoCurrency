package com.example.top100cryptocurrency.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.top100cryptocurrency.R
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        //возвращение на главную страницу
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //добавление баннера
        val adResult = AdRequest.Builder().build()
        adView.loadAd(adResult)
        //переход на страницу с рейтингом приложения
        buttonRateApp.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${applicationContext.packageName}")))
        }
    }
}