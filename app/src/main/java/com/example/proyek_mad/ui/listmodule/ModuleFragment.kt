package com.example.proyek_mad.ui.listmodule

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
import com.example.proyek_mad.R
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.databinding.FragmentModuleBinding


class ModuleFragment : Fragment() {
    lateinit var binding:FragmentModuleBinding
    val viewModel:ModuleViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_module, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.init()
        binding.lifecycleOwner = this

        fun updateProgress() {
            val max = viewModel.maxMateri.value ?: 1
            val current = MockDB.selectedMateri
            binding.progressIndicator.max = max
            binding.progressIndicator.progress = current
        }


        binding.btnPrevious.setOnClickListener {
            viewModel.previous()
        }

        binding.btnNext.setOnClickListener {
            viewModel.next()
        }

        binding.btnBackModules.setOnClickListener {
            var action = ModuleFragmentDirections.actionGlobalCourseDetailFragment()
            findNavController().navigate(action)
        }

        val observer = Observer<List<Module>>{it ->
            binding.txtModuleTitleCard.setText(it[0].judul_materi.toString())
            binding.txtModuleContent.setText(it[0].konten_materi.toString())
            if (MockDB.selectedMateri == 1) {
                binding.btnPrevious.isEnabled = false
            } else {
                binding.btnPrevious.isEnabled = true
            }

            if (MockDB.selectedMateri == viewModel.maxMateri.value) {
                binding.btnNext.isEnabled = false
            } else {
                binding.btnNext.isEnabled = true
            }

            updateProgress()


        }
        viewModel.module.observe(viewLifecycleOwner, observer)

        val observerToast = Observer<String>{it ->
            Toast.makeText(this.context, it.toString(), Toast.LENGTH_SHORT).show()
        }
        viewModel.toastku.observe(viewLifecycleOwner, observerToast)


    }

}