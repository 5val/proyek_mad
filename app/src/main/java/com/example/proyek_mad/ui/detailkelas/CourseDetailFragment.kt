package com.example.proyek_mad.ui.detailkelas

import android.os.Bundle
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
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.databinding.FragmentCourseDetailBinding

class CourseDetailFragment : Fragment() {
    lateinit var binding:FragmentCourseDetailBinding
    val viewModel:CourseDetailViewModel by viewModels<CourseDetailViewModel>{ MyViewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_course_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val courseDetailAdapter = CourseDetailAdapter()
        binding.vm = viewModel
        binding.lifecycleOwner = this
        viewModel.fetchEnrollment()

        val observer = Observer<Result<List<Module>>>{it ->
            it.onSuccess {list->
                courseDetailAdapter.submitList(list)
            }.onFailure { error->
                Log.e("error", error.toString(), )
                Toast.makeText(this.context, "Error connecting", Toast.LENGTH_SHORT).show()
            }

        }
        viewModel.materi.observe(viewLifecycleOwner, observer)
        viewModel.enrollment.observe(viewLifecycleOwner){it->
            it.onSuccess {
                courseDetailAdapter.last_kelas = it.materi_terakhir_diakses_id?:0
                viewModel.refresh()
            }.onFailure {
                Toast.makeText(this.context, "Error fetching database", Toast.LENGTH_SHORT).show()
            }
        }

        courseDetailAdapter.onItemClickListener =  {m ->
            var action = CourseDetailFragmentDirections.actionGlobalModuleFragment()
            findNavController().navigate(action)
        }

        binding.btnStartQuiz.setOnClickListener {
            findNavController().navigate(R.id.action_courseDetailFragment_to_quizFragment)
        }

        binding.rvModules.adapter = courseDetailAdapter
        binding.rvModules.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
    }


}