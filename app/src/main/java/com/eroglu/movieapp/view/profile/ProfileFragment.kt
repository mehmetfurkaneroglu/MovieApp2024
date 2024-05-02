package com.eroglu.movieapp.view.profile
/*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eroglu.movieapp.R


class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

}

 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eroglu.movieapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        // SharedPreferences'ten kullanıcı bilgilerini al
//        val sharedPreferences = requireActivity().getSharedPreferences(Constants.DATABASE_NAME, Context.MODE_PRIVATE)
//        val email = sharedPreferences.getString(Keys.USER_EMAIL, "")
//        val password = sharedPreferences.getString(Keys.USER_PASSWORD, "")
//
//        // Kullanıcı bilgilerini TextView'lere ata
//        binding.tvEmail.text = "$email"
//        binding.tvPassword.text = "$password"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
