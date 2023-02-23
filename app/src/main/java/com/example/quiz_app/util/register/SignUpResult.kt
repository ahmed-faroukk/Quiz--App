package com.example.quiz_app.util.register

import com.example.quiz_app.model.signupResponse.signUpResponse

sealed interface SignUpResult{
    data class Success( val response : signUpResponse) : SignUpResult
    data class Error (val Exception: Exception ) : SignUpResult
}