package com.example.quiz_app.ui.ViewModel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.quiz_app.util.NetworkHandle.ConnectivityObserver
import com.example.quiz_app.util.NetworkHandle.NetworkConnectivityObserver
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class BaseViewModel(myapp : Application) : AndroidViewModel(myapp) {
    lateinit var connectivityObserver: ConnectivityObserver

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext


    init {

            checkNetwork()
    }

    var active: MutableLiveData<Boolean> = MutableLiveData()

    fun checkNetwork() {
        connectivityObserver = NetworkConnectivityObserver(context)
        connectivityObserver.observe().onEach { it ->
            if (it.toString() == "Lost" || it.toString() == "Losing" || it.toString() == "Unavailable") {
                active.postValue(false)
            } else {
                active.postValue(true)
            }
        }.launchIn(viewModelScope)
    }


}