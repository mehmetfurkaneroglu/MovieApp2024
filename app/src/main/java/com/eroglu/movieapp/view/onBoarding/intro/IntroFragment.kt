package com.eroglu.movieapp.view.onBoarding.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.eroglu.movieapp.databinding.FragmentIntroBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class IntroFragment : Fragment() {

    private var _binding: FragmentIntroBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_intro, container, false)
        _binding = FragmentIntroBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getStartedButton.setOnClickListener{
            val action =
                IntroFragmentDirections.actionIntroFragmentToLoginFragment() // XXFragmentDirections hazır oluşturuluyor. Action da hazır
            Navigation.findNavController(it).navigate(action) //navigasyon. it =hangi nav controller içinde olucam. navigate  nereye gideceğimi söylüyorum (navigate= oraya doğru git) burayada aksiyonumu veriyorum
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}