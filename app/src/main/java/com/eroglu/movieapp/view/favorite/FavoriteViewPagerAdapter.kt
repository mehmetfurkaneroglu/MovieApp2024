package com.eroglu.movieapp.view.favorite

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FavoriteViewPagerAdapter(fragmentActivity: FavoriteFragment): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FavoriteFragmentMovies()
            1 -> FavoriteFragmentTvSeries()
            else -> FavoriteFragmentMovies()
        }
    }
}