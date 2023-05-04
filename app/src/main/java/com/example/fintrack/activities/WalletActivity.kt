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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        clAddCard = findViewById(R.id.clAddCard)
        imgVCard = findViewById(R.id.imgVCard)
        tvAddCard = findViewById(R.id.tvAddCard)
        clChooseCard = findViewById(R.id.clChooseCard)
        imgVVisa = findViewById(R.id.imgVVisa)


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
    }//end of onCreate
}//end of WalletActivity