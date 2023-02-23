package com.example.quiz_app.repository

import com.example.quiz_app.api.RetrofitInstance
import com.example.quiz_app.model.loginResponse.FinishedExam
import com.example.quiz_app.model.loginResponse.loginBody
import com.example.quiz_app.model.signupResponse.SignupBody
import com.example.quiz_app.model.loginResponse.response
import com.example.quiz_app.model.quizResponse.QuizBody
import okhttp3.OkHttpClient

class QuizRepository {

    private val clint = OkHttpClient()
    lateinit var response: response
    private var userToken: String? = null

    fun setUserToken(userToken: String) {
        this.userToken = userToken
    }

    fun getUserToken(): String? {
        return userToken
    }

    //remote data
    suspend fun signUp(
        userData: SignupBody,
    ) = RetrofitInstance().api.signUp(userData)

    suspend fun Login(
        UserData: loginBody,
    ) = RetrofitInstance().api.logIn(UserData)

    suspend fun getQuiz(token: String, finished_exam: QuizBody) =
        RetrofitInstance().api.getQuiz(token, finished_exam)


}