
package com.example.fintrack.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.example.fintrack.R
import com.example.fintrack.R.id.etCVV
import com.example.fintrack.models.CardModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

class AddCardActivity : AppCompatActivity() {

    //initializing variables
    private lateinit var btnBackWT : ImageView

    //form variables
    private lateinit var etCardNo : EditText
    private lateinit var etName : EditText
    private lateinit var etExpiryDate : EditText
    private lateinit var etCVV : EditText
    private lateinit var btnAdd : Button

    //Menu bar
    private lateinit var ibWallet : ImageButton

    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)

        btnBackWT = findViewById(R.id.btnBackWT)

        etCardNo = findViewById(R.id.etCardNo)
        etName = findViewById(R.id.etName)
        etExpiryDate = findViewById(R.id.etExpiryDate)
        etCVV = findViewById(R.id.etCVV)
        btnAdd = findViewById(R.id.btnAdd)

        ibWallet = findViewById(R.id.ibWallet)

        dbRef = FirebaseDatabase.getInstance().getReference("Cards")

        btnAdd.setOnClickListener {
            saveCardData()
        }


        val firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()

        btnBackWT.setOnClickListener {
            val intent = Intent(this, WalletActivity::class.java)
            startActivity(intent)
        }

        ibWallet.setOnClickListener {
            val intent = Intent(this, WalletActivity::class.java)
            startActivity(intent)
        }


    }

    private fun saveCardData(){

        //getting values
        val cardNo = etCardNo.text.toString()
        val cardName = etName.text.toString()
        val cardExpiryDate = etExpiryDate.text.toString()
        val cardCVV = etCVV.text.toString()

        if (cardNo.isEmpty()){
            etCardNo.error = "Please enter card number"
            return
        }
        if (cardName.isEmpty()){
            etName.error = "Please enter card name"
            return
        }
        if (cardExpiryDate.isEmpty()){
            etExpiryDate.error = "Please enter expiry date"
            return
        }
        if (cardCVV.isEmpty()){
            etCVV.error = "Please enter cvv number"
            return
        }

        val cardId = dbRef.push().key!!

        val card = CardModel(cardId, cardNo, cardName, cardExpiryDate, cardCVV)

        dbRef.child(cardId).setValue(card)
            .addOnCompleteListener {
                Toast.makeText(this,"Card Added successfully", Toast.LENGTH_LONG).show()

                etCardNo.text.clear()
                etName.text.clear()
                etExpiryDate.text.clear()
                etCVV.text.clear()
            }.addOnFailureListener { err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }


}