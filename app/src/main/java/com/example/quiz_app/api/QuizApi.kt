package com.example.quiz_app.api

import com.example.quiz_app.model.loginResponse.FinishedExam
import com.example.quiz_app.model.loginResponse.loginBody
import com.example.quiz_app.model.signupResponse.SignupBody
import com.example.quiz_app.model.loginResponse.response
import com.example.quiz_app.model.quizResponse.QuizBody
import com.example.quiz_app.model.quizResponse.quizResponse
import com.example.quiz_app.model.signupResponse.signUpResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface QuizApi {

    @POST("/user/sign")
    suspend fun signUp(
        @Body userData: SignupBody,
    ): signUpResponse

    @POST("/user/login")
    suspend fun logIn(
        @Body userData: loginBody,
    ): response
    @Headers("Accept:application/json")
    @POST("/quiz/get")
    suspend fun getQuiz(@Header("token") token: String,
                        @Body finished_exam : QuizBody): quizResponse
}