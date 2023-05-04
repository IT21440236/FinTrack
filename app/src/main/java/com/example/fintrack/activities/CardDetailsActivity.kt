package com.example.fintrack.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.fintrack.R

class CardDetailsActivity : AppCompatActivity() {

    private lateinit var tvCardId: TextView
    private lateinit var tvCardNo: TextView
    private lateinit var tvCardName: TextView
    private lateinit var tvCardExpiryDate: TextView
    private lateinit var tvCardCVV: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("cardId").toString(),
                intent.getStringExtra("cardNo").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("cardId").toString()
            )
        }
    }

    private fun deleteRecord(
        id:String
    ){

    }
}