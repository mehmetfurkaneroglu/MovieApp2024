package com.eroglu.movieapp.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import com.eroglu.movieapp.databinding.FragmentDetailBinding
import com.eroglu.movieapp.model.MovieResult
import com.eroglu.movieapp.util.Keys
import com.eroglu.movieapp.util.getMSerializable


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get()= _binding!!

    private var movie : MovieResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movie = arguments?.getMSerializable(Keys.MOVIE_DETAIL_KEY,MovieResult::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        binding.setVariable(BR.movieResult, movie)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /*
        bindingAdapter üzerinden verdim
        context?.let {
            Glide.with(it)
                .load("${BASE_IMG_URL}${movie?.posterPath}")
                .into(binding.picDetail)
        }
         */

        /*
        // veriyi databinding ile xml'den veriyorum
        binding.movieNameTextView.text = movie?.title.toString()
        binding.movieStar.text = movie?.voteAverage.toString()
        binding.movieSummary.text = movie?.overview
        binding.movieRelease.text = movie?.releaseDate
        binding.moviebisey.text = movie?.originalLanguage
         */

//        binding.backImageView.setOnClickListener {
//            //Navigation.findNavController(view).popBackStack()
//            // NavController'ü alın
//            val navController = findNavController()
//            // Geri işlemini yapın
//            navController.popBackStack()
//            //Bu kod parçası geri butonuna tıklandığında, NavController aracılığıyla geri işlemi yapar ve böylece MainActivity içindeki Navigation Graph'taki bir önceki fragmenta döner.
//        }


    }

    fun observe(){

    }

}