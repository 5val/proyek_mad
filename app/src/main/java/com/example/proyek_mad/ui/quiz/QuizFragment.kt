package com.example.proyek_mad.ui.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.proyek_mad.MyViewModelFactory
import com.example.proyek_mad.R
import com.example.proyek_mad.data.Question
import com.example.proyek_mad.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {
    lateinit var binding: FragmentQuizBinding
    val viewModel: QuizViewModel by activityViewModels<QuizViewModel> { MyViewModelFactory}

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
            binding.quizProgress.progress = it.urutan_soal - 1
            if(it.urutan_soal == viewModel.jmlQuestion) {
                binding.btnSubmit.text = "Submit Answer"
            } else {
                binding.btnSubmit.text = "Next"
            }
        }
        viewModel.question.observe(viewLifecycleOwner, observer)

        val observer2 = Observer<String>{ it ->
            if(viewModel.sisaWaktu.value == "00:00"){
                Toast.makeText(requireContext(), "Waktu pengerjaan sudah habis", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_quizFragment_to_quizResultFragment)
            }
        }
        viewModel.sisaWaktu.observe(viewLifecycleOwner, observer2)



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
                findNavController().navigate(R.id.action_quizFragment_to_quizResultFragment)
            } else {
                viewModel.changeQuestion()
            }
            binding.rgAnswers.clearCheck()
        }
    }
}