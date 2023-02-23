package com.example.quiz_app.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.quiz_app.R
import com.example.quiz_app.model.loginResponse.Value
import com.example.quiz_app.model.loginResponse.loginBody
import com.example.quiz_app.ui.ViewModel.BaseViewModel
import com.example.quiz_app.ui.MainActivity
import com.example.quiz_app.ui.QuizActivity
import com.example.quiz_app.ui.ViewModel.RegistrationViewModel
import com.example.quiz_app.util.register.LoginResult
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

class UserLoginFragment : Fragment(R.layout.fragment_user_login) {
    lateinit var signUpTv: TextView
    lateinit var phoneEt: EditText
    lateinit var passwordET: EditText
    lateinit var loginBtn: Button
    lateinit var googleBtn: Button
    lateinit var facebookBtn: Button
    lateinit var progressBar: ProgressBar
    lateinit var viewModel: RegistrationViewModel
    lateinit var BaseViewModel: BaseViewModel
    lateinit var token : String
    lateinit var responseValue: Value
    var login = false
    var counter = 0
    var checkNetwork: Boolean = false
    lateinit var rememberMe : CheckBox


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        phoneEt = view.findViewById(R.id.FirstNameET)
        passwordET = view.findViewById(R.id.LastNameET)
        loginBtn = view.findViewById(R.id.SIgnUpBtn)
        googleBtn = view.findViewById(R.id.GoogleBtn)
        facebookBtn = view.findViewById(R.id.FacebookBtn)
        signUpTv = view.findViewById(R.id.tv_SignUp)
        progressBar = view.findViewById(R.id.progressBarLogin)
        rememberMe = view.findViewById(R.id.checkBox)
        BaseViewModel = (activity as MainActivity).baseViewModel
        viewModel = (activity as MainActivity).viewModel

        progressBar.visibility = View.INVISIBLE
        //check Network
        BaseViewModel.active.observe(viewLifecycleOwner, Observer {
            if (it == false) {
                checkNetwork = false
                Snackbar.make(view, "Lost Connection", Snackbar.LENGTH_LONG).show()
                counter++
            } else {
                checkNetwork = true

            }
            if (checkNetwork == true && counter > 0)
                Snackbar.make(view, "back online", Snackbar.LENGTH_LONG).show()

        })



        // progress bar handling
        viewModel.logInIsLoading.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.INVISIBLE
            }
        })


        // go to signUp fragment
        signUpTv.setOnClickListener(View.OnClickListener {


            if (checkNetwork) {
                view.findNavController()
                    .navigate(UserLoginFragmentDirections.actionUserLoginFragmentToUserSignUpFragment())
                counter = 0
            } else
                Snackbar.make(view, "no Internet !!", Snackbar.LENGTH_LONG).show()
        })


        // send user data to the server
        loginBtn.setOnClickListener {
            val user = loginBody(phoneEt.text.toString(), passwordET.text.toString())
            viewModel.login(user)

        }

        //getToken
        viewModel.token.observe(viewLifecycleOwner , Observer {
            token = it
        })
        //get response.value
        viewModel.Responseval.observe(viewLifecycleOwner, Observer {
            responseValue = it
        })

            //The first argument is the name of the SharedPreferences file and
            // the second argument is the mode. MODE_PRIVATE means that the file can only be accessed by your app.
        val sharedPreferences = requireContext().getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
        val phone = sharedPreferences.getString("phone" , null)
        val pass  = sharedPreferences.getString("password" , null)
        if(phone!= null && pass!=null){
            requireContext().startActivity(
                Intent(requireActivity(), QuizActivity::class.java)
            )
        }
        viewModel.loginResult.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is LoginResult.Success -> {
                    if(rememberMe.isChecked){
                        val editor = sharedPreferences.edit()
                        editor.putString("phone", phoneEt.text.toString())
                        editor.putString("password", passwordET.text.toString())
                        editor.putString("Token" , token)
                        val gson = Gson()
                        val json : String = gson.toJson(responseValue)
                        editor.putString("responseValue" , json)
                        editor.apply()
                    }
                    requireContext().startActivity(
                        Intent(requireActivity(), QuizActivity::class.java)
                    )

                }

                is LoginResult.Error -> {
                    Snackbar.make(view, "phone or password is wrong", Snackbar.LENGTH_LONG).show()
                    Log.d("SnackBarError" , result.Exception.toString())
                }
            }

        })
    }



}