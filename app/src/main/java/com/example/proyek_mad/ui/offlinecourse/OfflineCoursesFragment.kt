package com.example.proyek_mad.ui.offlinecourse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyek_mad.R
import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.databinding.FragmentOfflineCoursesBinding
import com.example.proyek_mad.ui.offlineofflineCourse.OfflineCoursesAdapter


class OfflineCoursesFragment : Fragment() {
    lateinit var binding:FragmentOfflineCoursesBinding
    val viewModel:OfflineCoursesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_offline_courses,container,  false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var offlineCourseAdapter = OfflineCoursesAdapter()

        binding.lifecycleOwner = this
        val observer = Observer<List<Course>>{ it ->
            offlineCourseAdapter.submitList(it)
        }
        viewModel.offlineCourses.observe(viewLifecycleOwner, observer)
        viewModel.refresh()
        offlineCourseAdapter.onItemClickListener = {course->
            MockDB.selectedKelas = course.kelas_id
            findNavController().navigate(R.id.action_offlineCoursesFragment_to_courseDetailFragment)
        }
        offlineCourseAdapter.onDeleteClickListener = {course->
            viewModel.deleteCourse(course)
        }

        binding.rvOfflineCourses.adapter = offlineCourseAdapter
        binding.rvOfflineCourses.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

    }
}