package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.fintrack.R

class KidsLogin : AppCompatActivity() {

    private lateinit var btnKidLog: Button
    private lateinit var etKidLog: EditText
    private lateinit var btnKidReport: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kids_login)

        btnKidLog = findViewById(R.id.btnKidLog)
        etKidLog = findViewById(R.id.etKidLog)
        btnKidReport = findViewById(R.id.btnKidReport)

        btnKidLog.setOnClickListener {
            val name = etKidLog.text.toString().trim()

            if (name.isNotEmpty()) {
                val intent = Intent(this, KidsSpendAmountBalance::class.java).apply {
                    putExtra("NAME", name)
                }
                startActivity(intent)
                etKidLog.text.clear()
            }
        }

        btnKidReport.setOnClickListener {
//            val intent = Intent(this, KidsReport::class.java)
//            startActivity(intent)
            val name = etKidLog.text.toString().trim()

            if (name.isNotEmpty()) {
                val intent = Intent(this, KidsReport::class.java).apply {
                    putExtra("NAME", name)
                }
                startActivity(intent)
                etKidLog.text.clear()
            }
        }

    }
}