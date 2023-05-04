package com.example.fintrackp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fintrackp.R
import com.example.fintrackp.models.ContactusModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ContactusActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etMessage: EditText
    private lateinit var btnContactus: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactus)

        etName = findViewById(R.id.etNameCU)
        etEmail = findViewById(R.id.etEmailCU)
        etMessage = findViewById(R.id.etMessageCU)
        btnContactus = findViewById(R.id.btnSubmitCU)

        dbRef = FirebaseDatabase.getInstance().getReference("Contactus")

        btnContactus.setOnClickListener {
            saveContactusData()
        }
    }

    private fun saveContactusData(){
        val name = etName.text.toString()
        val email = etEmail.text.toString()
        val message = etMessage.text.toString()

        if(name.isEmpty()){
            etName.error = "Please enter name"
        }
        if(email.isEmpty()){
            etEmail.error = "Please enter email"
        }
        if(message.isEmpty()){
            etMessage.error = "Please enter message"
        }

        val contactId = dbRef.push().key!!

        val contact = ContactusModel(contactId, name, email, message)

        dbRef.child(contactId).setValue(contact)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etName.text.clear()
                etEmail.text.clear()
                etMessage.text.clear()

                startActivity(Intent(this@ContactusActivity, UserActivity::class.java))

            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}