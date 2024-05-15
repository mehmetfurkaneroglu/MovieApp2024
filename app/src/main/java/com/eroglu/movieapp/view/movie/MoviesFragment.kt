package com.eroglu.movieapp.view.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.eroglu.movieapp.BR
import com.eroglu.movieapp.R
import com.eroglu.movieapp.databinding.FragmentMoviesBinding
import com.eroglu.movieapp.model.movies.MovieResult
import com.eroglu.movieapp.util.Keys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by viewModels()
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        observe()

//        val images = listOf(
//            R.drawable.wide,
//            R.drawable.wide1,
//            R.drawable.wide3,
//            R.drawable.pikachu1,
//        )

//        val adapter = ViewPagerAdapter(images)
//        binding.viewpager2Slider.adapter = adapter
        binding.viewpager2Slider.adapter = viewModel.topRatedAdapter

//        binding.viewpager2Slider.beginFakeDrag() //sahte bir sürükleme işlemini başlatır.
//        binding.viewpager2Slider.fakeDragBy(-10f) //belirli bir mesafe kadar sürüklemeyi sağlar.
//        binding.viewpager2Slider.endFakeDrag()

    }

    private fun observe() {
        viewModel.selectedMovie.observe(viewLifecycleOwner){movie ->
            movie.let {
                navigateToMovieDetail(it)
            }
        }
    }

    private fun navigateToMovieDetail(movie: MovieResult){
        val bundle = Bundle().apply {
            putInt(Keys.MOVIE_DETAIL_KEY,movie.id?:0)
        }

        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
            .navigate(R.id.action_mainFragment_to_detailFragment,bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}