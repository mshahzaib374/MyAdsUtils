package com.shahzaib374.myadsutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.adsutilshelper.AddNumber
import com.example.adsutilshelper.GoogleAdsmobAdsUtils

class MainActivity : AppCompatActivity() {
    var addNumber: AddNumber? = null
    var googleAdsmobAdsUtils: GoogleAdsmobAdsUtils? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addNumber = AddNumber()
        googleAdsmobAdsUtils = GoogleAdsmobAdsUtils(this)

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            googleAdsmobAdsUtils!!.loadInterstitialAd("ca-app-pub-3940256099942544/1033173712")

        }, 1000)

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            Toast.makeText(this, "${addNumber!!.addNumber(2, 5)}", Toast.LENGTH_SHORT).show()

            googleAdsmobAdsUtils!!.showInterstitialAd(this, null, false)
        }, 6500)
    }
}