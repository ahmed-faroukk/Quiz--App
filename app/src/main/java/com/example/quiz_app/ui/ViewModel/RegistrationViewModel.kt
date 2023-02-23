package com.example.quiz_app.ui.ViewModel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.quiz_app.model.loginResponse.Value
import com.example.quiz_app.util.NetworkHandle.ConnectivityObserver
import com.example.quiz_app.model.loginResponse.loginBody
import com.example.quiz_app.model.signupResponse.SignupBody
import com.example.quiz_app.model.signupResponse.signUpResponse
import com.example.quiz_app.repository.QuizRepository
import com.example.quiz_app.util.register.LoginResult
import com.example.quiz_app.util.register.SignUpResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.Exception

class RegistrationViewModel(val quizRepository: QuizRepository, application: Application) :
    AndroidViewModel(application) {
    lateinit var tokenObject: String
    lateinit var connectivityObserver: ConnectivityObserver

    // get app context
    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    // myLiveData
    var signUpIsLoading: MutableLiveData<Boolean> = MutableLiveData()
    var logInIsLoading: MutableLiveData<Boolean> = MutableLiveData()

    private var _loginResult: MutableLiveData<LoginResult> = MutableLiveData()
    var loginResult: LiveData<LoginResult> = _loginResult

    private val _signUpResult: MutableLiveData<SignUpResult> = MutableLiveData()
    var signUpResult: LiveData<SignUpResult> = _signUpResult

    var token : MutableLiveData<String> = MutableLiveData()
    var Responseval : MutableLiveData<Value> = MutableLiveData()


    fun signUp(user: SignupBody) {

        viewModelScope.launch(Dispatchers.Main) {
            signUpIsLoading.postValue(true)

            try {

                val result : signUpResponse = quizRepository.signUp(user)

                if (result.value == "account created") {
                    _signUpResult.value = SignUpResult.Success(result)

                } else {
                    _signUpResult.value =
                        SignUpResult.Error(java.lang.Exception(result.value))
                }

            } catch (e: Exception) {
                _signUpResult.value = SignUpResult.Error(e)
            }

        }.invokeOnCompletion {
            signUpIsLoading.postValue(false)
        }

    }

    lateinit var result : LoginResult
    @SuppressLint("SuspiciousIndentation")
    fun login(sendUser: loginBody) {

        logInIsLoading.postValue(true)

        viewModelScope.launch(Dispatchers.Main) {
            try {

                val result = quizRepository.Login(sendUser)
                    quizRepository.setUserToken(result.token)
                token.value = result.token
                Responseval.value = result.value


                Log.d("showForMeQuizQuesToken", result.token)
                if (result.success) {
                    _loginResult.postValue(LoginResult.Success(result))

                } else {

                    _loginResult.value = LoginResult.Error(Exception(result.value.toString()))


                }

            } catch (e: Exception) {
                _loginResult.value = LoginResult.Error(e)
            }


        }.invokeOnCompletion {
            logInIsLoading.postValue(false)
        }
    }


}