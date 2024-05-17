package com.eroglu.movieapp.view.detail.tvSeries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eroglu.movieapp.BR
import com.eroglu.movieapp.databinding.FragmentTvSeriesDetailBinding
import com.eroglu.movieapp.model.FavoriteItemTypeEnum
import com.eroglu.movieapp.util.Keys
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvSeriesDetailFragment : Fragment() {

    private val viewModel: TvSeriesDetailViewModel by viewModels()
    private var _binding: FragmentTvSeriesDetailBinding? = null
    private val binding get() = _binding!!

    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getInt(Keys.TV_SERIES_DETAIL_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvSeriesDetailBinding.inflate(inflater, container, false)
        binding.setVariable(BR.tvViewModel, viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        id?.let {
            viewModel.getTvSeriesDetail(it)
        }

        var database = FirebaseDatabase.getInstance().reference
        binding.favoriteImageView.setOnClickListener {
            var itemId = viewModel.selectedTvSeriesDetail.value?.id?:0
            var itemName = viewModel.selectedTvSeriesDetail.value?.name
            var itemPicture = viewModel.selectedTvSeriesDetail.value?.posterPath
            var itemImdb = viewModel.selectedTvSeriesDetail.value?.voteAverage?:0.0

            val itemInfo = HashMap<String, Any>()
            itemInfo["itemName"] = itemName!!
            itemInfo["itemPicture"] = itemPicture!!
            itemInfo["itemId"] = itemId
            itemInfo["itemImdb"] = itemImdb
            itemInfo["itemType"] = FavoriteItemTypeEnum.TV_SERIES.name

            database
                .child("users")
                .child(Firebase.auth.currentUser?.uid.toString())
                .child("favorite_tv_series")
                .child(itemId.toString())
                .setValue(itemInfo)

        }
    }
}