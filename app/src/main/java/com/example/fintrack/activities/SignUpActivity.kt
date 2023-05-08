package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.fintrack.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    private lateinit var etUserName:EditText
    private lateinit var etUserEmail:EditText
    private lateinit var etNic:EditText
    private lateinit var etPhone:EditText
    private lateinit var etPassword:EditText
    private lateinit var etConfirmPassword:EditText
    private lateinit var btnSignUp:Button
    private lateinit var tvLogin: TextView

    private  lateinit var firebaseAuth: FirebaseAuth

    var databaseReference :  DatabaseReference? = null
    var database: FirebaseDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etUserName = findViewById(R.id.usernameET)
        etUserEmail = findViewById(R.id.emailEt)
        etNic = findViewById(R.id.nicET)
        etPhone = findViewById(R.id.phoneET)
        etPassword = findViewById(R.id.passET)
        etConfirmPassword = findViewById(R.id.confirmPassEt)
        btnSignUp = findViewById(R.id.button)
        tvLogin = findViewById(R.id.textView)

        firebaseAuth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        tvLogin.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        btnSignUp.setOnClickListener {
            val username = etUserName.text.toString()
            val useremail = etUserEmail.text.toString()
            val nic = etNic.text.toString()
            val phone = etPhone.text.toString()
            val pass = etPassword.text.toString()
            val confirmpass = etConfirmPassword.text.toString()

            if (useremail.isNotEmpty() && username.isNotEmpty() && nic.isNotEmpty() && phone.isNotEmpty() && pass.isNotEmpty() && confirmpass.isNotEmpty()) {
                if (pass == confirmpass){
                    firebaseAuth.createUserWithEmailAndPassword(useremail, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val currentUser = firebaseAuth.currentUser
                            val currentUSerDb = databaseReference?.child((currentUser?.uid!!))

                            currentUSerDb?.child("username")?.setValue(username)
                            currentUSerDb?.child("useremail")?.setValue(useremail)
                            currentUSerDb?.child("tel")?.setValue(phone)
                            currentUSerDb?.child("nic")?.setValue(nic)



                            val intent = Intent(this, SignInActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }
                }else{
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}