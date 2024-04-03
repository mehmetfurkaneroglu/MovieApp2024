package com.eroglu.movieapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.eroglu.movieapp.R
import com.eroglu.movieapp.databinding.ActivityMainBinding
import com.eroglu.movieapp.view.main.MainFragment
import com.eroglu.movieapp.view.menu.FavoriteFragment
import com.eroglu.movieapp.view.menu.ProfileFragment
import com.eroglu.movieapp.view.menu.TvSeriesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // BottomNavigationView'dan seçilen öğeleri dinleme
        binding.bottomNavBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.moviesFragment -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragmentContainerView, MainFragment())
                    }
                    true
                }
                R.id.tvSeriesFragment -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragmentContainerView, TvSeriesFragment())
                    }
                    true
                }
                R.id.favoriteFragment -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragmentContainerView, FavoriteFragment())
                    }
                    true
                }
                R.id.profileFragment -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragmentContainerView, ProfileFragment())
                    }
                    true
                }
                else -> false
            }
        }
    }
}
