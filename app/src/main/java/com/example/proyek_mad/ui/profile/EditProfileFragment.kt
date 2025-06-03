package com.example.proyek_mad.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.proyek_mad.MyViewModelFactory
import com.example.proyek_mad.R
import com.example.proyek_mad.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {
    lateinit var binding:FragmentEditProfileBinding
    val viewModel:ProfileViewModel by viewModels<ProfileViewModel> { MyViewModelFactory };

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.user = viewModel.profile.value
        viewModel.init()
        binding.btnSave.setOnClickListener{
            if (viewModel.validatePassword(
                binding.inpPasswordEdit.text.toString(),
                binding.inpConfirmPasswordEdit.text.toString()
            )){
                viewModel.updateProfile(
                    binding.inpNameReg.text.toString(),
                    binding.inpPasswordEdit.text.toString()
                )

            } else{
                Toast.makeText(this.context, "Password Tidak Sesuai", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnCancel.setOnClickListener{
            findNavController().navigate(R.id.action_global_profileFragment)
        }
        viewModel.profile.observe(viewLifecycleOwner){it->
            binding.user = it
        }
        viewModel.editSuccess.observe(viewLifecycleOwner){it->
            it.onSuccess {
                Toast.makeText(this.context, it.msg, Toast.LENGTH_SHORT).show()
                viewModel.updateMockDB()
                findNavController().navigate(R.id.action_global_profileFragment)
            }.onFailure {
                Toast.makeText(this.context, it.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

}