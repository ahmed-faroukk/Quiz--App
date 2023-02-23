package com.example.quiz_app.model.loginResponse

data class response(
    val success: Boolean,
    val token: String,
    val value: Value
)