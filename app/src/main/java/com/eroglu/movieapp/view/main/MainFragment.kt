package com.eroglu.movieapp.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.eroglu.movieapp.R
import com.eroglu.movieapp.databinding.FragmentMainBinding
import com.eroglu.movieapp.model.MovieResult
import com.eroglu.movieapp.util.Keys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    //private val popularMoviesAdapter = PopularMoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun observe() {
        /*
        //bu error listesinin kontrolunü xml üzerinde bindingAdapters ile veriyorum.
        viewModel.popularMoviesList.observe(viewLifecycleOwner) { movieList ->
            when (movieList) {
                is Resource.Success -> {
                    binding.popularMoviesProgressBar.visibility = View.GONE
//                        movieList.data?.results?.let {
//                            //(binding.bestMoviesRecyclerView.adapter as MoviesAdapter).list = it
//                            viewModel.moviesAdapter.list = it
//                        }
                }
                is Resource.Error -> {
                    binding.popularMoviesProgressBar.visibility = View.GONE
                    movieList.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    binding.popularMoviesProgressBar.visibility = View.VISIBLE
                }
            }
        }
         */
        /*

        viewModel.upcomingMoviesList.observe(viewLifecycleOwner) { movieList ->
            when (movieList) {
                is Resource.Success -> {
                    binding.upcomingMoviesProgressBar.visibility = View.GONE
                }
                is Resource.Error -> {
                    binding.upcomingMoviesProgressBar.visibility = View.GONE
                    movieList.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    binding.upcomingMoviesProgressBar.visibility = View.VISIBLE
                }
            }
        }

         */

        viewModel.selectedPopularMovie.observe(viewLifecycleOwner){movie ->
            movie.let {
                navigateToMovieDetail(it)
            }
        }

        viewModel.selectedUpcommingMovie.observe(viewLifecycleOwner){movie ->
            movie.let {
                navigateToMovieDetail(it)
            }
        }
    }

    private fun navigateToMovieDetail(movie: MovieResult){
        val bundle = Bundle().apply {
            putSerializable(Keys.MOVIE_DETAIL_KEY,movie)
        }

        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
            .navigate(R.id.action_mainFragment_to_detailFragment,bundle)
    }

    /*
    xmlden adapterı verdik
    private fun observer(){
        viewModel.popularMoviesList.observe(viewLifecycleOwner) {
            it.results?.let { list ->
                popularMoviesAdapter.list = list
                binding.bestMoviesRecyclerView.adapter = popularMoviesAdapter
            }
        }
    }

     */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}