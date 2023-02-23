package com.example.quiz_app.model.signupResponse

data class SignupBody(val email : String, val phone : String,
                      val first_name:String,
                      val last_name : String,
                      val password : String ) {

}
