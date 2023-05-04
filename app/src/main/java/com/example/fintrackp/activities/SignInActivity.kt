package com.example.fintrackp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.fintrackp.R
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    private lateinit var etUserEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var tvRegister:TextView

    private lateinit var btnSignIn: Button
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        etUserEmail = findViewById(R.id.emailEt)
        etPass= findViewById(R.id.passET)
        tvRegister= findViewById(R.id.textView)
        btnSignIn = findViewById(R.id.button)

        firebaseAuth = FirebaseAuth.getInstance()

        val currentuser = firebaseAuth.currentUser
        if(currentuser != null){
            startActivity(Intent(this@SignInActivity, UserActivity::class.java))
            finish()
        }

        tvRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        btnSignIn.setOnClickListener {
            val email = etUserEmail.text.toString()
            val pass = etPass.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this@SignInActivity, UserActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            }else{
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }


        }


    }
}