package com.example.moviesapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityRegisterBinding
import com.example.moviesapp.model.ViewModelUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnRegistration.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val confirm = binding.etConfirm.text.toString()
            var address = ""

            if (password == confirm) {
                addUser(username,password,confirm, address)
                startActivity(Intent(this, LoginActivity::class.java))
            } else if (password != confirm) {
                Toast.makeText(this, "Password wrong", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun addUser(username: String,
                        password: String,
                        confirm: String,
                        address: String) {
        val viewModel = ViewModelProvider(this)[ViewModelUser::class.java]
        viewModel.callPostUser(username, password,confirm,address)
        viewModel.postLiveDataUser().observe(this) {
            if (it != null) {
                Toast.makeText(this, "Succesed", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
    }
}