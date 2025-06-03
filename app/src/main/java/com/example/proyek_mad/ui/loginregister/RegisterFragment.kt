package com.example.proyek_mad.ui.loginregister

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.proyek_mad.R


class RegisterFragment : Fragment() {
    private lateinit var viewModel: LoginRegisterViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        val name = view.findViewById<EditText>(R.id.inpNameReg)
        val email = view.findViewById<EditText>(R.id.inpEmailReg)
        val password = view.findViewById<EditText>(R.id.inpPassReg)
        val passwordConfirm = view.findViewById<EditText>(R.id.inpCpassReg)
        val btnRegister = view.findViewById<Button>(R.id.btnReg)
        val btnToLogin = view.findViewById<TextView>(R.id.linkToLogin)

        viewModel = ViewModelProvider(requireActivity()).get(LoginRegisterViewModel::class.java)

        btnRegister.setOnClickListener {
            val nameInput = name.text.toString().trim()
            val emailInput = email.text.toString().trim()
            val passwordInput = password.text.toString()
            val confirmPasswordInput = passwordConfirm.text.toString()

            if (nameInput.isEmpty() || emailInput.isEmpty() || passwordInput.isEmpty() || confirmPasswordInput.isEmpty()) {
                Toast.makeText(requireContext(), "Semua field harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                email.error = "Email tidak valid"
                return@setOnClickListener
            }

            if (passwordInput != confirmPasswordInput) {
                passwordConfirm.error = "Konfirmasi password tidak sesuai"
                return@setOnClickListener
            }

            if (viewModel.isEmailRegistered(emailInput)) {
                email.error = "Email sudah terdaftar"
                return@setOnClickListener
            }

            viewModel.register(nameInput, emailInput, passwordInput)
            Toast.makeText(requireContext(), "Registrasi berhasil", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        btnToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return view
    }


}