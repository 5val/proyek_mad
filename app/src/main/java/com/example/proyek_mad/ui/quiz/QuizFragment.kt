package com.example.proyek_mad.ui.quiz

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
import com.example.proyek_mad.data.Question
import com.example.proyek_mad.databinding.FragmentCourseDetailBinding
import com.example.proyek_mad.databinding.FragmentQuizBinding
import com.example.proyek_mad.ui.detailmateri.CourseDetailAdapter
import com.example.proyek_mad.ui.detailmateri.CourseDetailViewModel

class QuizFragment : Fragment() {
    lateinit var binding: FragmentQuizBinding
    val viewModel: QuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refresh()
        binding.vm = viewModel
        binding.lifecycleOwner = this

        val observer = Observer<Question>{ it ->
            binding.quizProgress.progress = viewModel.question.value.urutan_soal - 1
        }
        viewModel.question.observe(viewLifecycleOwner, observer)

//        courseDetailAdapter.onItemClickListener =  {m ->
//            var action = CourseDetailFragmentDirections.actionGlobalModuleFragment()
//            findNavController().navigate(action)
//        }

//        binding.btnStartQuiz.setOnClickListener {
//            findNavController().navigate(R.id.action_courseDetailFragment_to_quizFragment)
//        }
//
//        binding.rvModules.adapter = courseDetailAdapter
//        binding.rvModules.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        if(viewModel.question.value.urutan_soal == viewModel.jmlQuestion) {
            binding.btnSubmit.text = "Submit Answer"
        } else {
            binding.btnSubmit.text = "Next"
        }

        binding.quizProgress.max = viewModel.jmlQuestion

        binding.rgAnswers.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.rbAnswer1) {
                viewModel.selectOption(viewModel.options.value[0].pilihan_id)
            }else if(checkedId == R.id.rbAnswer2) {
                viewModel.selectOption(viewModel.options.value[1].pilihan_id)
            }else if(checkedId == R.id.rbAnswer3) {
                viewModel.selectOption(viewModel.options.value[2].pilihan_id)
            }else if(checkedId == R.id.rbAnswer4) {
                viewModel.selectOption(viewModel.options.value[3].pilihan_id)
            }
        }

        binding.btnSubmit.setOnClickListener {
            viewModel.submitAnswer()
            if(viewModel.question.value.urutan_soal == viewModel.jmlQuestion) {
                viewModel.finishQuiz()
                findNavController().navigate(R.id.action_quizFragment_to_courseDetailFragment)
            } else {
                viewModel.changeQuestion()
            }
            binding.rgAnswers.clearCheck()
            if(viewModel.question.value.urutan_soal == viewModel.jmlQuestion) {
                binding.btnSubmit.text = "Submit Answer"
            } else {
                binding.btnSubmit.text = "Next"
            }
        }
    }


}