package com.eroglu.movieapp.view.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.eroglu.movieapp.databinding.FragmentLoginBinding
import com.eroglu.movieapp.view.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    private var _binding: FragmentLoginBinding? = null
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
        //return inflater.inflate(R.layout.fragment_login, container, false)
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        auth = Firebase.auth

        binding.loginButton.setOnClickListener {
            val email = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // E-posta ve şifre ile giriş yapma
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
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

        /*binding.loginButton.setOnClickListener {
            // 1. Intent oluşturma
            val intent = Intent(requireContext(), MainActivity::class.java)

            // 2. Bundle oluşturma
            val bundle = Bundle()
            // Bundle'a istediğiniz verileri ekleyin
            bundle.putString("exampleKey", "exampleValue")

            // 3. Intent'e Bundle eklemek
            intent.putExtras(bundle)

            // 4. Intent'i başlatma
            requireContext().startActivity(intent)
        }

         */


        /*

        // burada navigation ile geçiş var login fraagmenttan navigation grapha
        binding.loginButton.setOnClickListener{
            val action = LoginFragmentDirections.actionLoginFragmentToNavigationGraph()
            Navigation.findNavController(it).navigate(action)
        }

         */

/*
        binding.loginButton.setOnClickListener {

            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()

            //if (username == "mehmetfurkaneroglu" && password == "123") {
            if (username == "" && password == "") {
                val action =LoginFragmentDirections.actionLoginFragmentToMainFragment()
                Navigation.findNavController(it).navigate(action)
            } else {
                // Hata mesajını ekrana yazdırabilirsiniz.
                Toast.makeText(requireContext(), "Kullanıcı adı veya şifre hatalı", Toast.LENGTH_SHORT).show()
            }
        }

 */


    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Oturum açmış kullanıcı varsa MainActivity'e yönlendirme
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}