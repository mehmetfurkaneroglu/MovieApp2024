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

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eroglu.movieapp.databinding.FragmentProfileBinding
import com.eroglu.movieapp.util.Constants.DATABASE_NAME
import com.eroglu.movieapp.util.Keys.HAS_ACTIVE_USER
import com.eroglu.movieapp.view.OnboardingActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

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
        binding.btnLogout.setOnClickListener {
                Firebase.auth.signOut()
            val intent = Intent(requireActivity(), OnboardingActivity::class.java)
            requireContext().getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE).edit().remove(HAS_ACTIVE_USER).apply()
//            requireContext().getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE).edit().clear().apply() // tüm hepsini siler.
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
