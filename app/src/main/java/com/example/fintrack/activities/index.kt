package com.example.fintrack.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.fintrack.R

class index : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        val ImgVArrow = findViewById<ImageView>(R.id.ImgVArrow)


        ImgVArrow.setOnClickListener{
            val intent= Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }


        val googleLoginButton = findViewById<View>(R.id.ImgVGoogleLogo)
        googleLoginButton.setOnClickListener {
            val googleLoginUri = Uri.parse("https://accounts.google.com/login")
            val googleLoginIntent = Intent(Intent.ACTION_VIEW, googleLoginUri)
            startActivity(googleLoginIntent)
        }

        val facebookLoginButton = findViewById<View>(R.id.ImgVFBLogo)
        facebookLoginButton.setOnClickListener {
            val facebookLoginUri = Uri.parse("https://www.facebook.com/login")
            val facebookLoginIntent = Intent(Intent.ACTION_VIEW, facebookLoginUri)
            startActivity(facebookLoginIntent)
        }
    }
}