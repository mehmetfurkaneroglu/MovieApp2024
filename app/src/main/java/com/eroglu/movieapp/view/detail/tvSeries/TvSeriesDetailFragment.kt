package com.eroglu.movieapp.view.detail.tvSeries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eroglu.movieapp.BR
import com.eroglu.movieapp.databinding.FragmentTvSeriesDetailBinding
import com.eroglu.movieapp.model.tvSeries.TVSeriesResult
import com.eroglu.movieapp.util.Keys
import com.eroglu.movieapp.util.getMSerializable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvSeriesDetailFragment : Fragment() {

    private val viewModel: TvSeriesDetailViewModel by viewModels()
    private var _binding: FragmentTvSeriesDetailBinding? = null
    private val binding get()= _binding!!

    private var tvseries : TVSeriesResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvseries = arguments?.getMSerializable(Keys.TV_SERIES_DETAIL_KEY, TVSeriesResult::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvSeriesDetailBinding.inflate(inflater,container,false)
        binding.setVariable(BR.tvViewModel, viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        tvseries?.id?.let {
            viewModel.getTvSeriesDetail(it)
        }
    }

}