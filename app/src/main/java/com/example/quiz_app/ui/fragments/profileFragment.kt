package com.example.quiz_app.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.quiz_app.R
import com.example.quiz_app.databinding.FragmentBookBinding
import com.example.quiz_app.databinding.FragmentProfileBinding
import com.example.quiz_app.ui.MainActivity
import com.example.quiz_app.ui.QuizActivity

class profileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val instanse = requireContext().getSharedPreferences("login_prefs" , Context.MODE_PRIVATE)

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.logoutBtn.setOnClickListener(View.OnClickListener {
            val editor = instanse.edit()
            editor.remove("phone")
            editor.remove("password")
            editor.apply()
            requireContext().startActivity(
                Intent(requireActivity() , MainActivity::class.java)
            )

        })


        return binding.root
    }


}

