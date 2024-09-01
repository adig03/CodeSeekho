package com.aditya1010.codeseekho

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SplashScreen2 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen2)

        // Handle window insets for edge-to-edge content
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Hide the action bar
        supportActionBar?.hide()

        // Enable full-screen mode
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // Start a new thread to simulate a splash screen delay
        Thread {
            try {
                Thread.sleep(1000) // Pause for 5 seconds
            } catch (ex: Exception) {
                ex.printStackTrace()
            } finally {
                // Move to the main activity after the splash screen
                auth = Firebase.auth
               val currentUser = auth.currentUser
                if(currentUser != null){
                    startActivity(Intent(this , MainActivity::class.java))
                    finish()
                }
                else{
                    startActivity(Intent(this , LoginPage::class.java))
                    finish()
                }
            }
        }.start()
    }
}

