package com.eroglu.movieapp.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.eroglu.movieapp.databinding.FragmentDetailBinding
import com.eroglu.movieapp.model.MovieResult
import com.eroglu.movieapp.util.Constants.BASE_IMG_URL
import com.eroglu.movieapp.util.getMSerializable


class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding

    private var movie : MovieResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movie = arguments?.getMSerializable("key",MovieResult::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            Glide.with(it)
                .load("${BASE_IMG_URL}${movie?.posterPath}")
                .into(binding.picDetail)
        }
        binding.movieNameTextView.text = movie?.title.toString()
        binding.movieStar.text = movie?.voteAverage.toString()
        binding.movieSummary.text = movie?.overview
        binding.movieRelease.text = movie?.releaseDate
        binding.moviebisey.text = movie?.originalLanguage

        binding.backImageView.setOnClickListener {
            // NavController'ü alın
            val navController = findNavController()
            // Geri işlemini yapın
            navController.popBackStack()
            //Bu kod parçası geri butonuna tıklandığında, NavController aracılığıyla geri işlemi yapar ve böylece MainActivity içindeki Navigation Graph'taki bir önceki fragmenta döner.
        }


    }

    fun observe(){

    }

}