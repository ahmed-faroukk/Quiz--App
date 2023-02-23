package com.example.quiz_app.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.quiz_app.R
import com.example.quiz_app.databinding.FragmentBookBinding
import com.example.quiz_app.model.quizResponse.Mcq
import com.example.quiz_app.model.quizResponse.Value
import com.example.quiz_app.model.quizResponse.quizResponse
import com.example.quiz_app.ui.QuizActivity
import com.example.quiz_app.ui.ViewModel.quizViewModel

class bookFragment : Fragment(R.layout.fragment_book) {
    private lateinit var binding: FragmentBookBinding
    lateinit var viewModel : quizViewModel
    lateinit var quiz : quizResponse
    lateinit var rightAnswer : String
     var questionNumber : Int = 0
    lateinit var UserToken : String
    var isLoading = true
    var x  : Int = 0
     var y = 0
     var Score = 0
    @SuppressLint("LongLogTag")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentBookBinding.inflate(inflater, container, false)
        viewModel = (activity as QuizActivity).viewModel
          hide()


            // data observation from view model//


                   //get mcq list in quiz var
        viewModel.quiz.observe(viewLifecycleOwner, Observer {
                quiz = it
        })



        // navigate to next question
        binding.NextBtn.setOnClickListener(View.OnClickListener {
            if(binding.NextBtn.text == "Start Now"){
                show()
                var couter = questionNumber+1
                binding.currentQuesCounter.text = "$couter/${quiz.value.mcq.size} "
                binding.questionText.text = quiz.value.mcq[questionNumber].question
                binding.choice1.text = quiz.value.mcq[questionNumber].answer1
                binding.choice2.text = quiz.value.mcq[questionNumber].answer2
                binding.choice3.text = quiz.value.mcq[questionNumber].answer3
                binding.choice4.text = quiz.value.mcq[questionNumber].answer4
                questionNumber++
                calculateScore()
            }else{

                Log.d("the score" , Score.toString() )

            if (questionNumber==quiz.value.mcq.size-1)
                binding.NextBtn.text = "Finish quiz Now"
            if (questionNumber==quiz.value.mcq.size)
                finish()

            if (questionNumber<quiz.value.mcq.size && CheckAnswer()) {
                var couter = questionNumber+1
                binding.currentQuesCounter.text = "$couter/${quiz.value.mcq.size} "
                binding.questionText.text = quiz.value.mcq[questionNumber].question
                binding.choice1.text = quiz.value.mcq[questionNumber].answer1
                binding.choice2.text = quiz.value.mcq[questionNumber].answer2
                binding.choice3.text = quiz.value.mcq[questionNumber].answer3
                binding.choice4.text = quiz.value.mcq[questionNumber].answer4
                calculateScore()
                questionNumber++
            }
            ReadyForNewQuestion()}
        })


        //handle color when clicked
        binding.choice1.setOnClickListener(View.OnClickListener {
            it.setBackgroundResource(R.drawable.selected_)
            binding.choice2.setBackgroundResource(R.drawable.home_bg)
            binding.choice3.setBackgroundResource(R.drawable.home_bg)
            binding.choice4.setBackgroundResource(R.drawable.home_bg)
            x = 1

        })
        binding.choice2.setOnClickListener(View.OnClickListener {
            it.setBackgroundResource(R.drawable.selected_)
            binding.choice1.setBackgroundResource(R.drawable.home_bg)
            binding.choice3.setBackgroundResource(R.drawable.home_bg)
            binding.choice4.setBackgroundResource(R.drawable.home_bg)
            x= 2

        })
        binding.choice3.setOnClickListener(View.OnClickListener {
            it.setBackgroundResource(R.drawable.selected_)
            binding.choice2.setBackgroundResource(R.drawable.home_bg)
            binding.choice1.setBackgroundResource(R.drawable.home_bg)
            binding.choice4.setBackgroundResource(R.drawable.home_bg)
            x=3

        })
        binding.choice4.setOnClickListener(View.OnClickListener {
            it.setBackgroundResource(R.drawable.selected_)
            binding.choice2.setBackgroundResource(R.drawable.home_bg)
            binding.choice3.setBackgroundResource(R.drawable.home_bg)
            binding.choice1.setBackgroundResource(R.drawable.home_bg)
            x = 4

        })




        return binding.root
    }


    fun CheckAnswer() : Boolean{

        if (x == 0) {
            Toast.makeText(context , "please choose answer" , Toast.LENGTH_SHORT).show()
          return  false
        }
            if (x == 1) {
                if (binding.choice1.text == quiz.value.mcq[questionNumber].answer) {
                    Score++
                }
                return true
            }
        if (x == 2) {
            if (binding.choice2.text == quiz.value.mcq[questionNumber].answer)
                Score++
            return true

        }
        if (x == 3) {

            if (binding.choice3.text == quiz.value.mcq[questionNumber].answer)
                Score++
            return true

        }
        if (x == 4) {

            if (binding.choice4.text == quiz.value.mcq[questionNumber].answer)
                Score++
            return true
        }
        return false
    }
    fun calculateScore(){

        if (x == 1) {
            if (binding.choice1.text == quiz.value.mcq[questionNumber].answer)
                Score++
        }
        if (x == 2) {
            if (binding.choice2.text == quiz.value.mcq[questionNumber].answer)
                Score++
        }
        if (x == 3) {
            if (binding.choice3.text == quiz.value.mcq[questionNumber].answer)
                Score++
        }
        if (x == 4) {
            if (binding.choice4.text == quiz.value.mcq[questionNumber].answer)
                Score++
        }
    }
    fun ReadyForNewQuestion(){
        x = 0
        binding.choice2.setBackgroundResource(R.drawable.home_bg)
        binding.choice3.setBackgroundResource(R.drawable.home_bg)
        binding.choice1.setBackgroundResource(R.drawable.home_bg)
        binding.choice4.setBackgroundResource(R.drawable.home_bg)

    }


    fun hide(){
        binding.choice1.visibility = View.INVISIBLE
        binding.choice2.visibility = View.INVISIBLE
        binding.choice3.visibility = View.INVISIBLE
        binding.choice4.visibility = View.INVISIBLE
        binding.questionText.text = "\n\n\n\n are you Sure you want to start quiz Now ?"
        binding.currentQuesCounter.visibility = View.INVISIBLE
        binding.A.text=""
        binding.B.text=""
        binding.C.text=""
        binding.D.text=""
        binding.NextBtn.text = "Start Now"

    }fun show(){
        binding.choice1.visibility = View.VISIBLE
        binding.choice2.visibility = View.VISIBLE
        binding.choice3.visibility = View.VISIBLE
        binding.choice4.visibility = View.VISIBLE
        binding.questionText.visibility = View.VISIBLE
        binding.currentQuesCounter.visibility = View.VISIBLE
        binding.A.text="A"
        binding.B.text="B"
        binding.C.text="C"
        binding.D.text="D"
        binding.NextBtn.text = "Next"

    }
    fun finish(){
        binding.choice1.visibility = View.INVISIBLE
        binding.choice2.visibility = View.INVISIBLE
        binding.choice3.visibility = View.INVISIBLE
        binding.choice4.visibility = View.INVISIBLE
        binding.NextBtn.visibility = View.INVISIBLE
        binding.A.text=""
        binding.B.text=""
        binding.C.text=""
        binding.D.text=""
        binding.questionText.text ="       \n\n\n\n\n\n       True Answers :  $Score True \n\n       False Answers :  ${questionNumber-Score} false \n\n       your mark is $Score/${quiz.value.mcq.size} \n\n       Thank you "

    }

}