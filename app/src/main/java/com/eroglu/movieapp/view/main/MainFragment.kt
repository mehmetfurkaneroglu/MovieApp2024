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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    val viewModel : MainViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    //private val popularMoviesAdapter = PopularMoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_main, container, false)
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.setVariable(BR.viewModel,viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel.getPopularMovies()
        //observe()
        observe()

    }
/*
    fun observe(){
        viewModel.selectedPopularMovie.observe(viewLifecycleOwner, Observer { selectedPopularMovie ->
            when(selectedPopularMovie) {
                is Resource.Success -> {
                    hideProgressBar()
                    (selectedPopularMovie.data as? MovieResult)?.let { movie ->
                        val bundle = Bundle().apply {
                            putSerializable("key", movie)
                        }

                        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                            .navigate(R.id.action_mainFragment_to_detailFragment, bundle)
                    }

                }
                is Resource.Error -> {
                    hideProgressBar()
                    (selectedPopularMovie.message as? String)?.let { message ->
                        Log.e(TAG, "An error occurred: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

 */


    /*
        fun observe(){
            viewModel.selectedPopularMovie.observe(viewLifecycleOwner, Observer { selectedPopularMovie ->
                when(selectedPopularMovie) {
                    is Resource.Success -> {
                        hideProgressBar()
                        selectedPopularMovie.data?.let { movie ->
                                val bundle = Bundle().apply {
                                    putSerializable("key",movie)
                                }

                                Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                                    .navigate(R.id.action_mainFragment_to_detailFragment,bundle)
                            }

                        }
                    is Resource.Error -> {
                        hideProgressBar()
                        selectedPopularMovie.message?.let { message->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                }
                })
        }

     */

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.progressBar2.visibility = View.GONE
        binding.progressBar3.visibility = View.GONE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar2.visibility = View.VISIBLE
        binding.progressBar3.visibility = View.VISIBLE
        isLoading = true
    }


    var isLoading = false



// bu aşağıdaki kodda progress bar hep duruyor

    fun observe(){
        viewModel.selectedPopularMovie.observe(viewLifecycleOwner){ selectedPopularMovie ->
            selectedPopularMovie?.let { movie ->
                val bundle = Bundle().apply {
                    putSerializable("key",movie)
                }



                Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                    .navigate(R.id.action_mainFragment_to_detailFragment,bundle)
            }

        }
        binding.progressBar.visibility = View.GONE
        binding.progressBar3.visibility = View.GONE
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