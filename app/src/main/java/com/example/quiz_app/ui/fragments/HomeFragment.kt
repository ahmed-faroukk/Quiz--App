package com.example.quiz_app.ui.fragments

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.quiz_app.R
import com.example.quiz_app.databinding.FragmentHomeBinding
import com.example.quiz_app.model.loginResponse.FinishedExam
import com.example.quiz_app.model.quizResponse.Mcq
import com.example.quiz_app.model.quizResponse.quizResponse
import com.example.quiz_app.ui.QuizActivity
import com.example.quiz_app.ui.ViewModel.quizViewModel
import kotlinx.coroutines.delay

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel : quizViewModel
     var isLoading = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = (activity as QuizActivity).viewModel
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            isLoading = it
        })



            binding.refreshLayout.setOnRefreshListener {
                val FinishedExam = FinishedExam(10 , "ahmed")
                // do request for quiz
                viewModel.getQuiz()
                if (isLoading){
                    binding.homeButton.text = "Start Quiz Now"
                    binding.homeButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25F)
                    binding.homeButton.setBackgroundResource(R.color.skyBlu)
                    binding.homeButton.setCompoundDrawables(null , null ,null , null)

                }


                binding.refreshLayout.isRefreshing = false
            }


        binding.homeButton.setOnClickListener {
            if (isLoading)
                findNavController().navigate(R.id.action_homeFragment2_to_bookFragment)
        }



        return binding.root
    }


}