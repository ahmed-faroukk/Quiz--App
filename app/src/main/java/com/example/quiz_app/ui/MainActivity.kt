 package com.example.quiz_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quiz_app.R
import com.example.quiz_app.repository.QuizRepository
import com.example.quiz_app.ui.ViewModel.BaseViewModel
import com.example.quiz_app.ui.ViewModel.RegistrationViewModel

 class MainActivity : AppCompatActivity() {
     lateinit var viewModel: RegistrationViewModel
     lateinit var baseViewModel: BaseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository  = QuizRepository()
        viewModel = RegistrationViewModel(repository, application)
        baseViewModel = BaseViewModel(application)




    }

}