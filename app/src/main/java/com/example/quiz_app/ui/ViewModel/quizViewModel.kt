package com.example.quiz_app.ui.ViewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.quiz_app.model.loginResponse.FinishedExam
import com.example.quiz_app.model.loginResponse.Value
import com.example.quiz_app.model.quizResponse.QuizBody
import com.example.quiz_app.model.quizResponse.quizResponse
import com.example.quiz_app.repository.QuizRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch

class quizViewModel(val repository: QuizRepository ,application: Application) : AndroidViewModel(application) {

val quiz : MutableLiveData<quizResponse> = MutableLiveData()
    val isLoading : MutableLiveData<Boolean> = MutableLiveData()

        //get Token from SharedPreferences
    val instanse = application.getSharedPreferences("login_prefs" , Context.MODE_PRIVATE)
     val token : String = instanse.getString("Token" , null).toString()
   // get object from SharedPreferences
    val gson =  Gson()
    val json : String? = instanse.getString("responseValue" , "")
    val value : Value? = gson.fromJson(json , Value::class.java)


    //need to modify
    val ArrayOfFinshedExam : List<FinishedExam> = value!!.finished_exam
  val finshed_exaam : QuizBody = QuizBody(ArrayOfFinshedExam)

    fun getQuiz(){
        try {
            isLoading.value = true
            viewModelScope.launch {
                val response = repository.getQuiz(token , finshed_exaam )
                    quiz.value = response
            }.invokeOnCompletion {
                isLoading.value = false
            }

        }catch (exception : Exception){
            Log.d("showForMeQuizQuesExce.." , exception.message.toString())
        }

        }






}