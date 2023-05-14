package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.fintrack.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class KidsSpend : AppCompatActivity() {

    private lateinit var edtSpendPg43: EditText
    private lateinit var ImgVPenguinPg43: ImageView
    private lateinit var dbBalanceRef: DatabaseReference
    private lateinit var dbSpendRef: DatabaseReference
    private lateinit var ImgVHomePg45: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kids_spend)

        edtSpendPg43 = findViewById(R.id.lb_savePg42)
        ImgVPenguinPg43 = findViewById(R.id.ImgVPenguinPg43)

        dbBalanceRef = FirebaseDatabase.getInstance().getReference("kids_balance")
        dbSpendRef = FirebaseDatabase.getInstance().getReference("kids_spend")

        ImgVHomePg45 = findViewById(R.id.ImgVHomePg45)

        ImgVHomePg45.setOnClickListener {
            val intent = Intent(this, KidsSpendAmountBalance::class.java)
            startActivity(intent)
        }

        ImgVPenguinPg43.setOnClickListener {
            val spendAmount = edtSpendPg43.text.toString().toInt()

            dbBalanceRef.get().addOnSuccessListener { balanceDataSnapshot ->
                if (balanceDataSnapshot.value != null) {
                    val previousBalance = balanceDataSnapshot.value.toString().toInt()
                    val newBalance = previousBalance - spendAmount
                    dbBalanceRef.setValue(newBalance)

                    dbSpendRef.get().addOnSuccessListener { spendDataSnapshot ->
                        val previousSpend = if (spendDataSnapshot.value != null) {
                            spendDataSnapshot.value.toString().toInt()
                        } else {
                            0
                        }
                        dbSpendRef.setValue(previousSpend + spendAmount)
                    }
                } else {
                    // Handle if the kids_balance is not present in the database
                }
            }
            Toast.makeText(this, "Your spent amount is added successfully", Toast.LENGTH_SHORT).show()
            edtSpendPg43.text.clear()
        }
    }
}