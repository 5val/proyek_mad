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
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.proyek_mad.MyViewModelFactory
import com.example.proyek_mad.R
import com.example.proyek_mad.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private lateinit var binding:FragmentRegisterBinding
    val viewModel: LoginRegisterViewModel by viewModels<LoginRegisterViewModel> { MyViewModelFactory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = binding.inpNameReg
        val email = binding.inpEmailReg
        val password = binding.inpPassReg
        val passwordConfirm = binding.inpCpassReg
        val btnRegister = binding.btnReg
        val btnToLogin = binding.linkToLogin

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

        }
        viewModel.registerMessage.observe(viewLifecycleOwner){it->
            it.onSuccess {
                Toast.makeText(this.context, "Berhasil register", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }.onFailure {
                Toast.makeText(this.context, "Gagal register", Toast.LENGTH_SHORT).show()
            }
        }

        btnToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}