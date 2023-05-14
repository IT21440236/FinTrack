package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.fintrack.R



class index : AppCompatActivity() {

    private lateinit var ImgVArrow : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)


        ImgVArrow = findViewById(R.id.ImgVArrow)

        ImgVArrow.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }
}