package com.example.proyek_mad.ui.askgemini

import android.os.Bundle
import android.util.Log
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
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.databinding.FragmentCourseDetailBinding
import com.example.proyek_mad.databinding.FragmentGeminiBinding
import com.example.proyek_mad.ui.detailkelas.CourseDetailAdapter
import com.example.proyek_mad.ui.detailkelas.CourseDetailFragmentDirections
import com.example.proyek_mad.ui.detailkelas.CourseDetailViewModel

class GeminiFragment : Fragment() {
    lateinit var binding: FragmentGeminiBinding
    val viewModel: GeminiViewModel by viewModels<GeminiViewModel>{ MyViewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gemini, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        val geminiAdapter = GeminiAdapter()

        viewModel.refresh()

        viewModel.arrChat.observe(viewLifecycleOwner){list->
            geminiAdapter.submitList(list)
        }
        viewModel.message.observe(viewLifecycleOwner){it->
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        }

        binding.btnBackChat.setOnClickListener {
            findNavController().navigate(R.id.action_geminiFragment_to_moduleFragment)
        }
        binding.btnSend.setOnClickListener{
            var textBoxInp = binding.inpChat

            viewModel.sendMessage(textBoxInp.text.toString())
            textBoxInp.setText("")
        }

        binding.rvChats.adapter = geminiAdapter
        binding.rvChats.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
    }
}