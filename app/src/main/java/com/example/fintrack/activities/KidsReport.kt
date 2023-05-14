package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.fintrack.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class KidsReport : AppCompatActivity() {

    private lateinit var lb_hint_amountPg34: TextView
    private lateinit var lb_hint_balancePg: TextView
    private lateinit var lb_hint_expensePg34: TextView
    private lateinit var tvKidsReport: TextView

    private lateinit var dbRefAmount: DatabaseReference
    private lateinit var dbRefSpend: DatabaseReference
    private lateinit var dbRefBalance: DatabaseReference

    private lateinit var ImgVHomePg45: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kids_report)

        lb_hint_amountPg34 = findViewById(R.id.lb_hint_amountPg34)
        lb_hint_balancePg = findViewById(R.id.lb_hint_balancePg)
        lb_hint_expensePg34 = findViewById(R.id.lb_hint_expensePg34)
        tvKidsReport = findViewById(R.id.tvKidsReport)

        dbRefAmount = FirebaseDatabase.getInstance().getReference("kids_total")
        dbRefSpend = FirebaseDatabase.getInstance().getReference("kids_spend")
        dbRefBalance = FirebaseDatabase.getInstance().getReference("kids_balance")

        ImgVHomePg45 = findViewById(R.id.ImgVHomePg45)

        ImgVHomePg45.setOnClickListener {
            val intent = Intent(this, KidsLogin::class.java)
            startActivity(intent)
        }

        val name = intent.getStringExtra("NAME")
        tvKidsReport.text = "Hello $name \uD83D\uDE00 \uD83D\uDC4B"

        dbRefAmount.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val amount = snapshot.value as? Long ?: 0
                lb_hint_amountPg34.text = amount.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database read error
            }
        })


        dbRefSpend.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val spend = snapshot.value as? Long ?: 0
                lb_hint_expensePg34.text = spend.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database read error
            }
        })

        dbRefBalance.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val balance = snapshot.value as? Long ?: 0
                lb_hint_balancePg.text = balance.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database read error
            }
        })
    }
}