package com.example.proyek_mad.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyek_mad.MyViewModelFactory
import com.example.proyek_mad.R
import com.example.proyek_mad.data.Chat
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.databinding.FragmentProfileBinding
import com.example.proyek_mad.ui.listkelas.CoursesAdapter


class ProfileFragment : Fragment() {
    lateinit var binding:FragmentProfileBinding
    val viewModel:ProfileViewModel by viewModels<ProfileViewModel>{ MyViewModelFactory};
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
        if(MockDB.onlineMode){
            viewModel.init()
            viewModel.updateMockDB()
        } else{
            binding.btnEditProfile.isEnabled = false
        }

        binding.btnEditProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }
        binding.btnLogout.setOnClickListener {
            MockDB.geminiChats = listOf(
                Chat(1, 1, "Hello, how can I help you?"),
            )
            activity?.finish()
        }
        viewModel.profile.observe(viewLifecycleOwner){it->
            binding.user = it
        }
        viewModel.getCompletedCourses()
        var completedCourseAdapter = CoursesAdapter()
        viewModel.completedCourses.observe(viewLifecycleOwner){it->
            it.onSuccess { list->
                completedCourseAdapter.submitList(list)
            }
        }
        binding.rvCompletedCoursesProfile.adapter = completedCourseAdapter
        binding.rvCompletedCoursesProfile.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

    }
}