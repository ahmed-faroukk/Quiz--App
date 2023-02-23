package com.example.quiz_app.util.register

import com.example.quiz_app.model.loginResponse.response

sealed interface LoginResult{
    data class Success( val response : response) : LoginResult
    data class Error (val Exception: Exception ) : LoginResult
}