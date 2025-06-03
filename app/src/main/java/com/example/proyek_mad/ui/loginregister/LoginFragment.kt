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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.proyek_mad.R
import com.example.proyek_mad.UserActivity


class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginRegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val email = view.findViewById<EditText>(R.id.inpEmailReg)
        val password = view.findViewById<EditText>(R.id.inpPassReg)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val btnToRegister = view.findViewById<TextView>(R.id.linkToReg)

        viewModel = ViewModelProvider(requireActivity()).get(LoginRegisterViewModel::class.java)

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
            if (viewModel.login(emailInput, passwordInput)) {
                Toast.makeText(requireContext(), "Login berhasil", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), UserActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Email atau password salah", Toast.LENGTH_SHORT).show()
            }
        }

        btnToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)

        }
        return view
    }
}