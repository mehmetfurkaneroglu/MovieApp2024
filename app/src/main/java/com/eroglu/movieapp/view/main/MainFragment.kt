package com.eroglu.movieapp.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.eroglu.movieapp.BR
import com.eroglu.movieapp.R
import com.eroglu.movieapp.databinding.FragmentMainBinding
import com.eroglu.movieapp.model.movies.MovieResult
import com.eroglu.movieapp.util.Keys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

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
        binding.lifecycleOwner = this
        observe()
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
            putSerializable(Keys.MOVIE_DETAIL_KEY,movie)
        }

        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
            .navigate(R.id.action_mainFragment_to_detailFragment,bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}