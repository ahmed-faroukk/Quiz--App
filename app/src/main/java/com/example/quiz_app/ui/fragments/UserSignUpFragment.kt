package com.example.quiz_app.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.quiz_app.R
import com.example.quiz_app.model.signupResponse.SignupBody
import com.example.quiz_app.ui.MainActivity
import com.example.quiz_app.ui.QuizActivity
import com.example.quiz_app.ui.ViewModel.BaseViewModel
import com.example.quiz_app.ui.ViewModel.RegistrationViewModel
import com.example.quiz_app.util.register.SignUpResult
import com.google.android.material.snackbar.Snackbar

class UserSignUpFragment : Fragment(R.layout.fragment_user_signup) {
    lateinit var first_name: EditText
    lateinit var last_name: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var confiermPass: EditText
    lateinit var phone: EditText
    lateinit var signUpBtn: Button
    lateinit var progress: ProgressBar
    lateinit var signUpData: SignupBody
    lateinit var viewModel: RegistrationViewModel
    lateinit var BaseViewModel: BaseViewModel

    var response = false
    var checkNetwork: Boolean = false
    var counter = 0
    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        first_name = view.findViewById(R.id.FirstNameET)
        last_name = view.findViewById(R.id.LastNameET)
        email = view.findViewById(R.id.EmailET)
        password = view.findViewById(R.id.PasswordEt)
        confiermPass = view.findViewById(R.id.ConfirmpasswordET)
        phone = view.findViewById(R.id.PhoneNumberET)
        signUpBtn = view.findViewById(R.id.SIgnUpBtn)
        progress = view.findViewById(R.id.progressbar)

        viewModel = (activity as MainActivity).viewModel
        progress.visibility = View.INVISIBLE

        BaseViewModel = (activity as MainActivity).baseViewModel




        viewModel.signUpIsLoading.observe(viewLifecycleOwner, Observer {
            if (it == true)
                progress.visibility = View.VISIBLE
            else {
                progress.visibility = View.INVISIBLE

            }
        })





        viewModel.signUpResult.observe(viewLifecycleOwner,  Observer { result->
            when(result){
               is SignUpResult.Success ->{

                       requireContext().startActivity(
                           Intent(requireActivity() , QuizActivity::class.java)
                       )
               }
                is SignUpResult.Error ->{
                    Snackbar.make(view , " phone is already exist" , Snackbar.LENGTH_LONG).show()
                }
            }
        })



        BaseViewModel.active.observe(viewLifecycleOwner, Observer {
            if (it == false) {
                checkNetwork = false
                Snackbar.make(view, "Lost Connection", Snackbar.LENGTH_LONG).show()
                counter++
            } else {
                checkNetwork = true

            }
            if (checkNetwork == true && counter>0)
                Snackbar.make(view, "back online", Snackbar.LENGTH_LONG).show()

        })




        signUpBtn.setOnClickListener(View.OnClickListener {

            if (!checkNetwork)
                Snackbar.make(view, "No Internet !!", Snackbar.LENGTH_LONG).show()
            if (checkSignUp()) {

                if(first_name.text.isNotEmpty() && last_name.text.isNotEmpty() &&
                    email.text.isNotEmpty() && password.text.isNotEmpty() &&
                    confiermPass.text.isNotEmpty() && phone.text.isNotEmpty()
                ) {

                    val signUpData = SignupBody(
                        email.text.toString(), phone.text.toString(),
                        first_name.text.toString(), last_name.text.toString(),
                        password.text.toString()
                    )
                            viewModel.signUp(signUpData)




                }
            }
        })


    }

    fun alert(context: Context) {
        val builder = AlertDialog.Builder(context, AppCompatActivity.TRIM_MEMORY_BACKGROUND)
        builder.setTitle("Android Alert")
        builder.setMessage("there is no Internet")

        builder.setPositiveButton("OK") { dialog, which ->
        }

        builder.show()

    }

    fun checkNum(num: String): Boolean {
        if (num.length != 11)
            return false

        if (num[2] == '0' || num[2] == '1' || num[2] == '2' || num[2] == '5') {
            if (num[0] == '0' && num[1] == '1')
                return true
        }

        return false


    }

    fun checkMail(Mail: String): Boolean {
        val patterns = Patterns.EMAIL_ADDRESS
        return patterns.matcher(Mail).matches()

    }
    fun checkPass(pass : String ) : Boolean{
        return pass.length >= 8

    }

    fun checkSignUp(): Boolean {

        var counter = 0
        if (first_name.text.isEmpty()) {
            first_name.setHintTextColor(Color.RED)
            counter++

        }
        if (last_name.text.isEmpty()) {
            last_name.setHintTextColor(Color.RED)
            counter++

        }
        if (email.text.isEmpty()) {
            email.setHintTextColor(Color.RED)
            counter++

        }
        if (password.text.isEmpty()) {
            password.setHintTextColor(Color.RED)
            counter++

        }
        if (confiermPass.text.isEmpty()) {
            confiermPass.setHintTextColor(Color.RED)
            counter++

        }
        if (phone.text.isEmpty()) {
            phone.setHintTextColor(Color.RED)
            counter++

        }
        if (!checkMail(email.text.toString())) {
            email.setHintTextColor(Color.RED)
            email.hint = "please enter correct Email"
            email.text = null
            counter++

        }
        if (!checkPass(password.text.toString())) {
            password.setHintTextColor(Color.RED)
            password.hint = "your pass must be more than 8 numbers "
            password.text = null


        }
        if (!checkPass(confiermPass.text.toString())) {
            confiermPass.setHintTextColor(Color.RED)
            confiermPass.hint = "your pass must be more than 8 numbers "
            confiermPass.text = null


        }
        if (!checkNum(phone.text.toString())) {
            phone.setHintTextColor(Color.RED)
            phone.hint = "please enter correct phone number"
            phone.text = null
            counter++

        }
        if (password.text.toString() != confiermPass.text.toString()) {
            confiermPass.text = null
            confiermPass.hint = "password is different "
            confiermPass.setHintTextColor(Color.RED)
            counter++

        }
        return counter == 0

    }

}