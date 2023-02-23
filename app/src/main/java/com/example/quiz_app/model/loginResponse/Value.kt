package com.example.quiz_app.model.loginResponse

data class Value(
    val __v: Int,
    val _id: String,
    val email: String,
    val finished_exam: List<FinishedExam>,
    val first_name: String,
    val last_name: String,
    val password: String,
    val phone: String
)