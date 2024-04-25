package com.eroglu.movieapp.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eroglu.movieapp.databinding.FragmentDetailBinding
import com.eroglu.movieapp.model.movies.MovieResult
import com.eroglu.movieapp.util.Keys
import com.eroglu.movieapp.util.getMSerializable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModels()
    private var _binding: FragmentDetailBinding? = null
    private val binding get()= _binding!!

    private var movie : MovieResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getMSerializable(Keys.MOVIE_DETAIL_KEY, MovieResult::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        binding.setVariable(BR.viewModel, detailViewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        movie?.id?.let {
            detailViewModel.getMovieDetail(it)
        }
    }

}