package com.example.proyek_mad.ui.detailkelas

import android.os.Bundle
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
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.databinding.FragmentCourseDetailBinding

class CourseDetailFragment : Fragment() {
    lateinit var binding:FragmentCourseDetailBinding
    val viewModel:CourseDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_course_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.refresh()
        val courseDetailAdapter = CourseDetailAdapter()
        binding.vm = viewModel
        binding.lifecycleOwner = this

        val observer = Observer<List<Module>>{it ->
            courseDetailAdapter.submitList(it)
        }
        viewModel.materi.observe(viewLifecycleOwner, observer)

//        courseDetailAdapter.onItemClickListener =  {m ->
//            var action = CourseDetailFragmentDirections.actionGlobalModuleFragment()
//            findNavController().navigate(action)
//        }

        binding.btnStartQuiz.setOnClickListener {
            findNavController().navigate(R.id.action_courseDetailFragment_to_quizFragment)
        }

        binding.rvModules.adapter = courseDetailAdapter
        binding.rvModules.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
    }


}