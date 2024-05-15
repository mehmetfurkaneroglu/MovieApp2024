package com.eroglu.movieapp.view.tvSeries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.eroglu.movieapp.BR
import com.eroglu.movieapp.R
import com.eroglu.movieapp.databinding.FragmentTvSeriesBinding
import com.eroglu.movieapp.model.tvSeries.TVSeriesResult
import com.eroglu.movieapp.util.Keys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvSeriesFragment : Fragment() {

    private val viewModel: TvSeriesViewModel by viewModels()
    private var _binding: FragmentTvSeriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvSeriesBinding.inflate(inflater, container, false)
        binding.setVariable(BR.tvViewModel, viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        observe()

//        val imagesTv = listOf(
//            R.drawable.pikachu1,
//            R.drawable.pikachu3,
//            R.drawable.pikachu5,
//            R.drawable.wide,
//            R.drawable.wide1,
//            R.drawable.wide3,
//
//        )

//        val adapter = ViewPagerAdapter(imagesTv)
//        binding.viewpagerSlider.adapter = adapter
//        binding.viewpagerSlider.adapter = ViewPagerAdapter(imagesTv)
        binding.viewpagerSlider.adapter = viewModel.topRatedTvAdapter

//        binding.viewpagerSlider.beginFakeDrag() //sahte bir sürükleme işlemini başlatır.
//        binding.viewpagerSlider.fakeDragBy(-10f) //belirli bir mesafe kadar sürüklemeyi sağlar.
//        binding.viewpagerSlider.endFakeDrag()

    }

    private fun observe() {
        viewModel.selectedTvSeries.observe(viewLifecycleOwner){tvSeries ->
            tvSeries.let {
                navigateToTvSeriesDetail(it)
            }
        }
    }

    private fun navigateToTvSeriesDetail(tvSeries: TVSeriesResult){
        val bundle = Bundle().apply {
            putInt(Keys.TV_SERIES_DETAIL_KEY,tvSeries.id?:0)
        }

        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
            .navigate(R.id.action_tvSeriesFragment_to_tvSeriesDetailFragment,bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}