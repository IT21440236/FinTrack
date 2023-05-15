package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.fintrack.R
import java.text.DecimalFormat

class GoalReco : AppCompatActivity() {
    private lateinit var etAmount: EditText
    private lateinit var etTime: EditText
    private lateinit var etAge: EditText
    private lateinit var tvRec1: TextView
    private lateinit var tvRec2: TextView
    private lateinit var tvRec3: TextView
    private lateinit var btnCalc: Button

    private lateinit var btnBack: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_reco)

        etAmount = findViewById(R.id.etGoalAmount1)
        etTime = findViewById(R.id.etPaymentTimeAGP1)
        etAge = findViewById(R.id.etAge)
        tvRec1 = findViewById(R.id.textView14)
        tvRec2 = findViewById(R.id.textView13)
        tvRec3 = findViewById(R.id.textView7)
        btnCalc = findViewById(R.id.btnCheck1)

        btnBack=findViewById<ImageView>(R.id.btnBackAP)

        btnCalc.setOnClickListener {
            val fullAmount:Double = etAmount.text.toString().toDouble()
            val time:String = etTime.text.toString()
            val age:Int = etAge.text.toString().toInt()

//            val rec1 = fullAmount * (35.5/100)
//            val rec2 = fullAmount * (40.5/100)
//            val rec3 = fullAmount * (24.0/100)

            val df = DecimalFormat("#.##")
//            tvRec1.text = "Recommended goal 1: ${df.format(rec1)} for ${time}"
//            tvRec2.text = "Recommended goal 2: ${df.format(rec2)} for ${time}"
//            tvRec3.text = "Recommended goal 3: ${df.format(rec3)} for ${time}"

            if (age in 18..25){
                val rec1 = fullAmount * (35.5/100)
                val rec2 = fullAmount * (40.5/100)
                val rec3 = fullAmount * (24.0/100)

                tvRec1.text = "Education : ${df.format(rec1)} for ${time}"
                tvRec2.text = "Invest in stock market: ${df.format(rec2)} for ${time}"
                tvRec3.text = "Travel: ${df.format(rec3)} for ${time}"
            }else if(age in 26..30){
                val rec4 = fullAmount * (30.0/100)
                val rec5 = fullAmount * (45.0/100)
                val rec6 = fullAmount * (25.0/100)

                tvRec1.text = "Start Business: ${df.format(rec4)} for ${time}"
                tvRec2.text = "Major Purchase: ${df.format(rec5)} for ${time}"
                tvRec3.text = "Emergency fund: ${df.format(rec6)} for ${time}"
            }
        }

        btnBack.setOnClickListener {
            startActivity(Intent(this@GoalReco, GoalActivity::class.java))
        }


    }
}