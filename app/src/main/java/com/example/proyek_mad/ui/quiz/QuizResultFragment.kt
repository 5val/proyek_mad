package com.example.proyek_mad.ui.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.proyek_mad.MyViewModelFactory
import com.example.proyek_mad.R
import com.example.proyek_mad.data.Question
import com.example.proyek_mad.databinding.FragmentQuizBinding
import com.example.proyek_mad.databinding.FragmentQuizResultBinding

class QuizResultFragment : Fragment() {
    lateinit var binding: FragmentQuizResultBinding
    val viewModel: QuizViewModel by activityViewModels<QuizViewModel> { MyViewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_result, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel
        binding.lifecycleOwner = this

        binding.btnUlangi.setOnClickListener {
            viewModel.refresh()
            findNavController().navigate(R.id.action_quizResultFragment_to_quizFragment)
        }

        binding.btnBackQuiz.setOnClickListener {
            findNavController().navigate(R.id.action_quizResultFragment_to_courseDetailFragment)
        }

    }
}