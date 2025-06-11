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
import com.example.proyek_mad.MyViewModelFactory
import com.example.proyek_mad.R
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.databinding.FragmentModuleBinding


class ModuleFragment : Fragment() {
    lateinit var binding:FragmentModuleBinding
    val viewModel:ModuleViewModel by viewModels<ModuleViewModel>{ MyViewModelFactory}
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
        var minMateri = 0
        var maxMateri = 0


//        fun updateProgress() {
////            val max = viewModel.maxMateri.value ?: 1
//            val current = MockDB.selectedMateri
//            binding.progressIndicator.max = totalMateri
//            binding.progressIndicator.progress = current
//            Toast.makeText(this.context, totalMateri.toString(), Toast.LENGTH_SHORT).show()
//        }

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

        binding.btnAskGemini.setOnClickListener {
            findNavController().navigate(R.id.action_moduleFragment_to_geminiFragment)
        }
        if(!MockDB.onlineMode){
            binding.btnAskGemini.isEnabled = false
        }

        val observer = Observer<Result<Module>>{it ->
            it.onSuccess {
                binding.txtModuleTitleCard.setText(it.judul_materi.toString())
                binding.txtModuleContent.setText(it.konten_materi.toString())
                if (MockDB.selectedMateri == viewModel.minMateri.value) {
                    binding.btnPrevious.isEnabled = false
                } else {
                    binding.btnPrevious.isEnabled = true
                }
                if (MockDB.selectedMateri == viewModel.maxMateri.value) {
                    binding.btnNext.isEnabled = false
                } else {
                    binding.btnNext.isEnabled = true
                }
//                updateProgress()
            }
        }
        viewModel.module.observe(viewLifecycleOwner, observer)
        viewModel.minMateri.observe(viewLifecycleOwner){it->
//            minMateri = it
//            updateProgress()
            viewModel.refresh()
        }
        viewModel.maxMateri.observe(viewLifecycleOwner){it->
//            maxMateri = it
//            updateProgress()
            viewModel.refresh()
        }

        viewModel.urutanMateri.observe(viewLifecycleOwner){it->
            binding.progressIndicator.max = viewModel.totalMateri.value
            binding.progressIndicator.progress = it
        }

        val observerToast = Observer<String>{it ->
            Toast.makeText(this.context, it.toString(), Toast.LENGTH_SHORT).show()
        }
        viewModel.toastku.observe(viewLifecycleOwner, observerToast)

    }

}