package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fintrack.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class WalletActivity : AppCompatActivity() {

    //Variable Initialization
    private lateinit var clAddCard: ConstraintLayout
    private lateinit var imgVCard: ImageView
    private lateinit var tvAddCard: TextView
    private lateinit var clChooseCard: ConstraintLayout
    private lateinit var imgVVisa: ImageView

    private lateinit var clTransaction: ConstraintLayout
    private lateinit var imgVTransaction: ImageView
    private lateinit var tvTransaction: TextView

    private lateinit var clCalculator: ConstraintLayout
    private lateinit var imgVCalculator: ImageView
    private lateinit var tvCalculator: TextView






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        clAddCard = findViewById(R.id.clAddCard)
        imgVCard = findViewById(R.id.imgVCard)
        tvAddCard = findViewById(R.id.tvAddCard)
        clChooseCard = findViewById(R.id.clChooseCard)
        imgVVisa = findViewById(R.id.imgVVisa)

        clTransaction = findViewById(R.id.clTransaction)
        imgVTransaction = findViewById(R.id.imgVTransaction)
        tvTransaction = findViewById(R.id.tvTransaction)

        clCalculator = findViewById(R.id.clCalculator)
        imgVCalculator = findViewById(R.id.imgVCalculator)
        tvCalculator = findViewById(R.id.tvCalculator)




        val firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()

        clAddCard.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            startActivity(intent)
        }
        imgVCard.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            startActivity(intent)
        }
        tvAddCard.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            startActivity(intent)
        }
        clChooseCard.setOnClickListener {
            val intent = Intent(this, FetchingCardActivity::class.java)
            startActivity(intent)
        }
        imgVVisa.setOnClickListener {
            val intent = Intent(this, FetchingCardActivity::class.java)
            startActivity(intent)
        }

        clTransaction.setOnClickListener {
            val intent = Intent(this, MainActivityPg7::class.java)
            startActivity(intent)
        }
        imgVTransaction.setOnClickListener {
            val intent = Intent(this, MainActivityPg7::class.java)
            startActivity(intent)
        }
        tvTransaction.setOnClickListener {
            val intent = Intent(this, MainActivityPg7::class.java)
            startActivity(intent)
        }

        clCalculator.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }
        imgVCalculator.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }
        tvCalculator.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }
    }//end of onCreate
}//end of WalletActivity