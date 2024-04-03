package com.eroglu.movieapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eroglu.movieapp.databinding.ActivityOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity: AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SharedPreferences'ten onboarding gösterilip gösterilmediğini kontrol et
        val sharedPreferences = getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        val isFirstStart = sharedPreferences.getBoolean("isFirstStart", true)

        if (isFirstStart) {
            // Onboarding ekranını başlat
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)

            // Onboarding ekranının gösterildiğini kaydet
            val editor = sharedPreferences.edit()
            editor.putBoolean("isFirstStart", false)
            editor.apply()
        }
    }
}