package com.example.fintrack.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.fintrack.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class KidsAmount : AppCompatActivity() {

    private lateinit var edtAmountPg45: EditText
    private lateinit var dbRefBalance: DatabaseReference
    private lateinit var dbRefTotal: DatabaseReference
    private lateinit var ImgVPiggyBankPg45: ImageView
    private lateinit var ImgVHomePg45: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kids_amount)

        edtAmountPg45 = findViewById(R.id.lb_savePg42)
        ImgVPiggyBankPg45 = findViewById(R.id.ImgVPiggyBankPg45)

        dbRefBalance = FirebaseDatabase.getInstance().getReference("kids_balance")
        dbRefTotal = FirebaseDatabase.getInstance().getReference("kids_total")

        ImgVHomePg45 = findViewById(R.id.ImgVHomePg45)
        ImgVPiggyBankPg45.setOnClickListener {
            val amount = edtAmountPg45.text.toString().toInt()

            if (amount == null || amount <= 0) {
                edtAmountPg45.error = "Please enter a valid value"
                return@setOnClickListener
            }

            dbRefBalance.get().addOnSuccessListener {
                if (it.value != null) {
                    val previousBalance = it.value.toString().toInt()
                    dbRefBalance.setValue(amount + previousBalance)
                    dbRefTotal.get().addOnSuccessListener { snapshot ->
                        if (snapshot.value != null) {
                            val previousTotal = snapshot.value.toString().toInt()
                            dbRefTotal.setValue(amount + previousTotal)
                        } else {
                            dbRefTotal.setValue(amount)
                        }
                    }
                } else {
                    dbRefBalance.setValue(amount)
                    dbRefTotal.setValue(amount)
                }
            }
            Toast.makeText(this, "Your piggy bank updated successfully", Toast.LENGTH_SHORT).show()
            edtAmountPg45.text.clear()
        }

    }
}