package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.fintrack.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class KidsBalance : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference
    private lateinit var lb_savePg42: TextView
    private lateinit var ImgVHomePg45: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kids_balance)

        lb_savePg42 = findViewById(R.id.lb_savePg42)
        dbRef = FirebaseDatabase.getInstance().getReference("kids_balance")

        ImgVHomePg45 = findViewById(R.id.ImgVHomePg45)

        ImgVHomePg45.setOnClickListener {
            val intent = Intent(this, KidsSpendAmountBalance::class.java)
            startActivity(intent)
        }

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val balance = snapshot.value as? Long ?: 0
                lb_savePg42.text = balance.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database read error
            }
        })
    }
}