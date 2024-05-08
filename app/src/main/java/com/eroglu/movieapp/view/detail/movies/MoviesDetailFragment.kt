package com.eroglu.movieapp.view.detail.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eroglu.movieapp.BR
import com.eroglu.movieapp.databinding.FragmentDetailBinding
import com.eroglu.movieapp.util.Keys
import com.eroglu.movieapp.view.favorite.FavoriteItemTypeEnum
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesDetailFragment : Fragment() {

    private val detailViewModel: MoviesDetailViewModel by viewModels()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getInt(Keys.MOVIE_DETAIL_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.setVariable(BR.viewModel, detailViewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        id?.let {
            detailViewModel.getMovieDetail(it)
        }

        var database = FirebaseDatabase.getInstance().reference
        //Veri ekleme işlemi
        binding.favoriteImageView.setOnClickListener {
            var itemId = detailViewModel.selectedMovieDetail.value?.id.toString()
            var itemName = detailViewModel.selectedMovieDetail.value?.originalTitle
            var itemPicture = detailViewModel.selectedMovieDetail.value?.posterPath

            val itemInfo = HashMap<String, Any>()
            itemInfo["itemName"] = itemName!!
            itemInfo["itemPicture"] = itemPicture!!
            itemInfo["itemId"] = itemId
            itemInfo["itemType"] = FavoriteItemTypeEnum.MOVIES

            database
                .child("users")
                .child(Firebase.auth.currentUser?.uid.toString())
                .child("favorite_movies")
                .child(itemId)
                .setValue(itemInfo)
        }
    }
}