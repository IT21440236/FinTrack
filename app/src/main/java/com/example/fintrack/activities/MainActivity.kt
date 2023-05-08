package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fintrack.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    //Navigate to Add user page
    private lateinit var btnInsertDataUser: Button
    private lateinit var btnFetchDataUser: Button

    //Kid
    private lateinit var btnInsertDataKid: Button
    private lateinit var btnFetchDataKid: Button

    //kid section
    private lateinit var btnKid: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        //User
        btnInsertDataUser = findViewById(R.id.btnInsertDataUser)
        btnFetchDataUser = findViewById(R.id.btnFetchDataUser)

        //Kid
        btnInsertDataKid = findViewById(R.id.btnInsertDataKid)
        btnFetchDataKid = findViewById(R.id.btnFetchDataKid)

        //kid section
        btnKid = findViewById(R.id.btnKid)

        //User
        btnInsertDataUser.setOnClickListener {
            val intent = Intent(this, AddUser::class.java)
            startActivity(intent)
        }

        btnFetchDataUser.setOnClickListener {
            val intent = Intent(this, FetchingActivityUser::class.java)
            startActivity(intent)
        }

        //Kid
        btnInsertDataKid.setOnClickListener {
            val intent = Intent(this, AddKid::class.java)
            startActivity(intent)
        }

        btnFetchDataKid.setOnClickListener {
            val intent = Intent(this, FetchingActivityKid::class.java)
            startActivity(intent)
        }

        //kid section
        btnKid.setOnClickListener {
            val intent = Intent(this, KidsLogin::class.java)
            startActivity(intent)
        }
    }
}