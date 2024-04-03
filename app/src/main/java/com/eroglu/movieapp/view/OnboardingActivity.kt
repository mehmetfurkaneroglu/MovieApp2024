package com.eroglu.movieapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eroglu.movieapp.databinding.ActivityOnboardingBinding
import com.eroglu.movieapp.util.Constants
import com.eroglu.movieapp.util.Keys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity: AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SharedPreferences'ten onboarding gösterilip gösterilmediğini kontrol et
        val sharedPreferences = getSharedPreferences(Constants.DATABASE_NAME, Context.MODE_PRIVATE)
        val hasActiveUser = sharedPreferences.getBoolean(Keys.HAS_ACTIVE_USER, false)

        if (hasActiveUser){
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }

    }
}