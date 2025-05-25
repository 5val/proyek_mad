package com.example.proyek_mad.ui.listkelas

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyek_mad.R
import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.databinding.FragmentCoursesBinding


class CoursesFragment : Fragment() {
    lateinit var binding:FragmentCoursesBinding
    val viewModel:CoursesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_courses, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.refresh()
        val coursesAdapter = CoursesAdapter()
        binding.lifecycleOwner = this

        val observer = Observer<List<Course>>{ it ->
            coursesAdapter.submitList(it)
        }
        viewModel.courses.observe(viewLifecycleOwner, observer)

        coursesAdapter.onItemClickListener = {course->
            MockDB.selectedKelas = course.kelas_id
            findNavController().navigate(R.id.action_coursesFragment_to_courseDetailFragment)
        }

        binding.rvCourses.adapter = coursesAdapter
        binding.rvCourses.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        binding.inpSearchCourse.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.search(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}