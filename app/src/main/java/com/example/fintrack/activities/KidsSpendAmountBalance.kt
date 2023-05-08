package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.fintrack.R

class KidsSpendAmountBalance : AppCompatActivity() {

    private lateinit var btnSpendKidHome: Button
    private lateinit var btnAmountKidHome: Button
    private lateinit var btnBalanceKidHome: Button
    private lateinit var tvKidsHome: TextView
    private lateinit var ImgPiggyPg42: ImageView
    private lateinit var ImgPenguinPg42: ImageView
    private lateinit var ImgHoneyPg42: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kids_spend_amount_balance)

        btnSpendKidHome = findViewById(R.id.btnSpendKidHome)
        btnAmountKidHome = findViewById(R.id.btnAmountKidHome)
        btnBalanceKidHome = findViewById(R.id.btnBalanceKidHome)
        tvKidsHome = findViewById(R.id.tvKidsHome)
        ImgPiggyPg42 = findViewById(R.id.ImgPiggyPg42)
        ImgPenguinPg42 = findViewById(R.id.ImgPenguinPg42)
        ImgHoneyPg42 = findViewById(R.id.ImgHoneyPg42)

        val name = intent.getStringExtra("NAME")
        tvKidsHome.text = "Hello $name \uD83D\uDE00 \uD83D\uDC4B"

        btnAmountKidHome.setOnClickListener {
            val intent = Intent(this, KidsAmount::class.java)
            startActivity(intent)
        }

        btnSpendKidHome.setOnClickListener {
            val intent = Intent(this, KidsSpend::class.java)
            startActivity(intent)
        }

        btnBalanceKidHome.setOnClickListener {
            val intent = Intent(this, KidsBalance::class.java)
            startActivity(intent)
        }

        ImgPiggyPg42.setOnClickListener {
            val intent = Intent(this, KidsAmount::class.java)
            startActivity(intent)
        }

        ImgPenguinPg42.setOnClickListener {
            val intent = Intent(this, KidsSpend::class.java)
            startActivity(intent)
        }

        ImgHoneyPg42.setOnClickListener {
            val intent = Intent(this, KidsBalance::class.java)
            startActivity(intent)
        }
    }
}