package com.aditya1010.codeseekho

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.aditya1010.codeseekho.databinding.ActivityLoginPageBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginPage : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPageBinding
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginPageBinding.inflate(layoutInflater)

        binding.LoginButton.setOnClickListener {

    if(binding.loginEmail.text.toString().isEmpty() || binding.loginPassword.text.toString().isEmpty()) {
        binding.loginEmail.error =
            if (binding.loginEmail.text.toString().isEmpty()) "Not a Valid Email" else null

        binding.loginPassword.error =
            if (binding.loginPassword.text.toString().isEmpty()) "Password is required" else null

    }
            else {
        val user =
            UserData(binding.loginEmail.text.toString(), binding.loginPassword.text.toString())
        auth = Firebase.auth
        auth.signInWithEmailAndPassword(user.email!!, user.password!!).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Signed in successfull", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }



        }

        binding.goToSignUp.setOnClickListener{
            startActivity(Intent(this , signUpActivity::class.java))
        }
        setContentView(binding.root)
        }
    }
