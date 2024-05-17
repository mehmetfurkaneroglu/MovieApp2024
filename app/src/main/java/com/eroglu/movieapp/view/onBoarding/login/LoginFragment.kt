package com.eroglu.movieapp.view.onBoarding.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.eroglu.movieapp.databinding.FragmentLoginBinding
import com.eroglu.movieapp.util.Constants
import com.eroglu.movieapp.util.Keys
import com.eroglu.movieapp.view.MainActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val email = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // E-posta ve şifre ile giriş yapma

                Firebase.auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            val editor = requireActivity().getSharedPreferences(
                                Constants.DATABASE_NAME,
                                Context.MODE_PRIVATE
                            ).edit()
                            editor.putBoolean(Keys.HAS_ACTIVE_USER, true)
                            editor.apply()

                            // Giriş başarılı ise ana ekrana yönlendirme
                            val intent = Intent(requireContext(), MainActivity::class.java)
                            startActivity(intent)
                            requireActivity().finish()
                        } else {
                            // Giriş başarısız ise hata mesajı gösterme
                            Toast.makeText(
                                requireContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                // E-posta veya şifre alanı boşsa kullanıcıyı uyar
                Toast.makeText(
                    requireContext(), "Please enter email and password.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}