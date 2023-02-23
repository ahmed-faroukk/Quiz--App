package com.example.quiz_app.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.quiz_app.R
import com.example.quiz_app.repository.QuizRepository
import com.example.quiz_app.ui.ViewModel.BaseViewModel
import com.example.quiz_app.ui.ViewModel.RegistrationViewModel
import com.example.quiz_app.ui.ViewModel.quizViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class QuizActivity : AppCompatActivity() {
    lateinit var bottomNav : BottomNavigationView
    lateinit var navController: NavController
    lateinit var sharedPreferences : SharedPreferences
    lateinit var viewModel: quizViewModel
    lateinit var baseViewModel: BaseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        val repository  = QuizRepository()
        viewModel = quizViewModel(repository, application)
        baseViewModel = BaseViewModel(application)
        val intent = intent
        sharedPreferences = getSharedPreferences("login_prefs"  , MODE_PRIVATE)
        bottomNav = findViewById(R.id.bottomNav)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNav.setupWithNavController(navController)

    }


}