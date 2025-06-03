package com.example.proyek_mad.ui.loginregister

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.proyek_mad.MyViewModelFactory
import com.example.proyek_mad.R
import com.example.proyek_mad.UserActivity
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.databinding.FragmentCoursesBinding
import com.example.proyek_mad.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    lateinit var binding:FragmentLoginBinding
    val viewModel:LoginRegisterViewModel by viewModels<LoginRegisterViewModel> { MyViewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = binding.inpEmailReg
        val password = binding.inpPassReg
        val btnLogin = binding.btnLogin
        val btnToRegister = binding.linkToReg

        btnLogin.setOnClickListener {
            val emailInput = email.text.toString().trim()
            val passwordInput = password.text.toString()

            if (emailInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(requireContext(), "Email dan password harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                email.error = "Email tidak valid"
                return@setOnClickListener
            }
        }

        viewModel.loginSuccess.observe(viewLifecycleOwner){ it->
            if(it.user_id == 0){
                Toast.makeText(requireContext(), "Email atau password salah", Toast.LENGTH_SHORT).show()
            } else{
                MockDB.currentUser = it
                Toast.makeText(requireContext(), "Login berhasil", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), UserActivity::class.java)
                startActivity(intent)
            }
        }

        btnToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}