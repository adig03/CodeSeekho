package com.aditya1010.codeseekho

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aditya1010.codeseekho.databinding.ActivitySignUpPageBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class signUpActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignUpPageBinding
    private lateinit var auth:FirebaseAuth
    private var database = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpPageBinding.inflate(layoutInflater)

        binding.SignUpButton.setOnClickListener {
            auth = Firebase.auth
           var user=UserData(binding.UserName.text.toString().trim(),
               binding.signupEmail.text.toString(),
               binding.signuppassword.editText?.text.toString())
            if (binding.UserName.text.toString().isEmpty() ||
                binding.signupEmail.text.toString().isEmpty() ||
                binding.signuppassword.editText?.text.toString().isEmpty()
                ){
                Toast.makeText(this , "Fill All Details" , Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(user.email!! , user.password!!).addOnCompleteListener{
                    if (it.isSuccessful){


                        database.collection(Firebase.auth.currentUser?.uid.toString()).document(Firebase.auth.currentUser?.uid.toString()).set(user)
                        Toast.makeText(this , "Sign Up Successfull" , Toast.LENGTH_SHORT).show()
                        val i = Intent(this , MainActivity::class.java)
                        startActivity(i)


                        finish()
                    }
                    else{
                        Toast.makeText(this , "Sign Up Unsuccessfull" , Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
        binding.goToLoginText.setOnClickListener{
            startActivity(Intent(this , LoginPage::class.java))
        }
        setContentView(binding.root)

    }
}