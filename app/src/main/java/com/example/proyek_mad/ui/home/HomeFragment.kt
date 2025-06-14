package com.example.proyek_mad.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyek_mad.MyViewModelFactory
import com.example.proyek_mad.R
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.databinding.FragmentHomeBinding
import com.example.proyek_mad.ui.listkelas.CoursesAdapter
import com.example.proyek_mad.ui.listkelas.CoursesViewModel

class HomeFragment : Fragment() {
    lateinit var binding:FragmentHomeBinding
    val viewModel: HomeViewModel by viewModels<HomeViewModel>{ MyViewModelFactory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(MockDB.onlineMode){
            binding.refreshbtntv.visibility = View.GONE
            binding.offlineModetv.visibility = View.GONE
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
            binding.rvCompletedCoursesProfile.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

            viewModel.ongoingCourses.observe(viewLifecycleOwner) { result ->
                result.onSuccess { courseList ->
                    ongoingCourseAdapter.submitList(courseList)
                }.onFailure { error ->
//                Toast.makeText(this.context, "Error fetching ongoing", Toast.LENGTH_SHORT).show()
                }
            }
            binding.txtUserLevel.setText("Course selesai: 0")
            viewModel.completedCourses.observe(viewLifecycleOwner) { result ->
                result.onSuccess { courseList ->
                    completedCourseAdapter.submitList(courseList)
                    binding.txtUserLevel.setText("Course selesai: "+courseList.size.toString())
                }.onFailure { error ->
//                Toast.makeText(this.context, "Error fetching completed", Toast.LENGTH_SHORT).show()
                }
            }
            viewModel.getAll()
        } else{
            binding.rvOngoingCoursesHome.visibility = View.GONE
            binding.rvCompletedCoursesProfile.visibility = View.GONE
            binding.refreshbtntv.setOnClickListener{
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