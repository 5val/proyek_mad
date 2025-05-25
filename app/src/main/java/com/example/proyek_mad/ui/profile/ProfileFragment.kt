package com.example.proyek_mad.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.proyek_mad.R
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    lateinit var binding:FragmentProfileBinding
    val viewModel:ProfileViewModel by viewModels();
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.user = viewModel.profile.value
        viewModel.init()
        binding.btnEditProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }
        binding.btnLogout.setOnClickListener {
            activity?.finish()
        }
    }
}