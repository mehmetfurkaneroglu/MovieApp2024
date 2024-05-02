package com.eroglu.movieapp.view.detail.tvSeries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eroglu.movieapp.BR
import com.eroglu.movieapp.databinding.FragmentTvSeriesDetailBinding
import com.eroglu.movieapp.util.Keys
import com.eroglu.movieapp.view.favorite.FavoriteItemTypeEnum
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvSeriesDetailFragment : Fragment() {

    private val viewModel: TvSeriesDetailViewModel by viewModels()
    private var _binding: FragmentTvSeriesDetailBinding? = null
    private val binding get()= _binding!!

    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getInt(Keys.TV_SERIES_DETAIL_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvSeriesDetailBinding.inflate(inflater,container,false)
        binding.setVariable(BR.tvViewModel, viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        id?.let {
            viewModel.getTvSeriesDetail(it)
        }

        binding.favoriteImageView.setOnClickListener{
            var movieId = viewModel.selectedTvSeriesDetail.value?.id.toString()
            var movieName = viewModel.selectedTvSeriesDetail.value?.name
            var moviePicture = viewModel.selectedTvSeriesDetail.value?.posterPath

            val movieInfo = HashMap<String, Any>()
            movieInfo["movieName"] = movieName!!
            movieInfo["moviePicture"] = moviePicture!!
            movieInfo["movieId"] = movieId
            movieInfo["type"] = FavoriteItemTypeEnum.TV_SERIES.name

            FirebaseDatabase.getInstance().reference
                .child("favorite_movies")
                .child(Firebase.auth.currentUser?.uid.toString())
                .child(viewModel.selectedTvSeriesDetail.value?.id.toString())
                .setValue(movieInfo)

        }
    }

}