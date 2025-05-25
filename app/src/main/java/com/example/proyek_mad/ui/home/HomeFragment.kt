package com.example.proyek_mad.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyek_mad.R
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.databinding.FragmentHomeBinding
import com.example.proyek_mad.ui.listkelas.CoursesAdapter

class HomeFragment : Fragment() {
    lateinit var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var completedCourseAdapter = CoursesAdapter()
        var ongoingCourseAdapter = CoursesAdapter()

        completedCourseAdapter.onItemClickListener = {course ->
            MockDB.selectedKelas = course.kelas_id
            findNavController().navigate(R.id.action_global_courseDetailFragment)
        }
        ongoingCourseAdapter.onItemClickListener = {course ->
            MockDB.selectedKelas = course.kelas_id
            findNavController().navigate(R.id.action_global_courseDetailFragment)
        }

        binding.rvOngoingCoursesHome.adapter = ongoingCourseAdapter
        binding.rvOngoingCoursesHome.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        binding.rvCompletedCoursesProfile.adapter = completedCourseAdapter
        binding.rvOngoingCoursesHome.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)


    }

}