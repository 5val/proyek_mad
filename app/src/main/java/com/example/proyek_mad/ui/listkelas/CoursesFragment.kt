package com.example.proyek_mad.ui.listkelas

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import com.example.proyek_mad.MyViewModelFactory
import com.example.proyek_mad.R
import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.databinding.FragmentCoursesBinding


class CoursesFragment : Fragment() {
    lateinit var binding:FragmentCoursesBinding
    val viewModel:CoursesViewModel by viewModels<CoursesViewModel>{ MyViewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_courses, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(MockDB.onlineMode){
            binding.refreshtvc.visibility = View.GONE
            binding.refreshbtnc.visibility = View.GONE
            viewModel.refresh()
            val coursesAdapter = CoursesAdapter()
            binding.lifecycleOwner = this

            coursesAdapter.onItemClickListener = {course->
                viewModel.clickClass(course)
            }
            viewModel.message.observe(viewLifecycleOwner){it->
                it.onSuccess {
                    findNavController().navigate(R.id.action_coursesFragment_to_courseDetailFragment)
                }.onFailure {err->
                    Toast.makeText(this.context, err.message, Toast.LENGTH_SHORT).show()
                }
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
            viewModel.courses.observe(viewLifecycleOwner) { it ->
                it.onSuccess {
                        list->  coursesAdapter.submitList(list)
                }.onFailure { error ->
                    Toast.makeText(this.context, "Error fetching database", Toast.LENGTH_SHORT).show()
                }
            }
        } else{
            binding.refreshbtnc.setOnClickListener{
                viewModel.checkOnline()
            }
            viewModel.onlineCheck.observe(viewLifecycleOwner){it->
                if(it.user_id == 0){
                    Toast.makeText(this.context, "Gagal menyambung server", Toast.LENGTH_SHORT).show()
                } else{
                    MockDB.onlineMode = true
                    findNavController().navigate(R.id.action_global_homeFragment)
                }
            }
        }


    }
}