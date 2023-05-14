package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.fintrack.R

class Home : AppCompatActivity() {

    private lateinit var ImgVWalletPg6 : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        ImgVWalletPg6 = findViewById(R.id.ImgVWalletPg6)


        ImgVWalletPg6.setOnClickListener {
            val intent = Intent(this, WalletActivity::class.java)
            startActivity(intent)
        }
    }
}