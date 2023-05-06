package com.example.fintrack.activities

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.fintrack.R
import com.example.fintrack.models.CardModel
import com.google.firebase.database.FirebaseDatabase

class CardDetailsActivity : AppCompatActivity() {

    private lateinit var tvCardId: TextView
    private lateinit var tvCardNo: TextView
    private lateinit var tvCardName: TextView
    private lateinit var tvCardExpiryDate: TextView
    private lateinit var tvCardCVV: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    private lateinit var btnBackWT: ImageView

    //Menubar
    private lateinit var ibWallet: ImageButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_details)

        btnBackWT = findViewById(R.id.btnBackWT)
        ibWallet = findViewById(R.id.ibWallet)

        btnBackWT.setOnClickListener {
            val intent = Intent(this, FetchingCardActivity::class.java)
            startActivity(intent)
        }

        ibWallet.setOnClickListener {
            val intent = Intent(this, WalletActivity::class.java)
            startActivity(intent)
        }

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
        val dbRef = FirebaseDatabase.getInstance().getReference("Cards").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Card Deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this,FetchingCardActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this,"Deleting error ${error.message}", Toast.LENGTH_LONG).show()

        }

    }

    private fun initView(){
//        tvCardId = findViewById(R.id.tvCardId)
        tvCardNo = findViewById(R.id.tvCardNo)
        tvCardName = findViewById(R.id.tvCardName)
        tvCardExpiryDate = findViewById(R.id.tvCardExpiryDate)
        tvCardCVV = findViewById(R.id.tvCardCVV)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews(){
//        tvCardId.text = intent.getStringExtra("cardId")
        tvCardNo.text = intent.getStringExtra("cardNo")
        tvCardName.text = intent.getStringExtra("cardName")
        tvCardExpiryDate.text = intent.getStringExtra("cardExpiryDate")
        tvCardCVV.text = intent.getStringExtra("cardCVV")
    }

    private fun openUpdateDialog(
        cardId: String,
        cardNo: String
    ){

        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog,null)

        mDialog.setView(mDialogView)

        val etCardNo = mDialogView.findViewById<EditText>(R.id.etCardNo)
        val etCardName = mDialogView.findViewById<EditText>(R.id.etCardName)
        val etCardExpiryDate = mDialogView.findViewById<EditText>(R.id.etCardExpiryDate)
        val etCardCVV = mDialogView.findViewById<EditText>(R.id.etCardCVV)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etCardNo.setText(intent.getStringExtra("cardNo").toString())
        etCardName.setText(intent.getStringExtra("cardName").toString())
        etCardExpiryDate.setText(intent.getStringExtra("cardExpiryDate").toString())
        etCardCVV.setText(intent.getStringExtra("cardCVV").toString())

        mDialog.setTitle("Updating $cardNo")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateCardData(
                cardId,
                etCardNo.text.toString(),
                etCardName.text.toString(),
                etCardExpiryDate.text.toString(),
                etCardCVV.text.toString()

            )

            Toast.makeText(applicationContext,"Card Data Updated", Toast.LENGTH_LONG).show()

            //We are setting updated data to our textviews
            tvCardNo.text = etCardNo.text.toString()
            tvCardName.text = etCardName.text.toString()
            tvCardExpiryDate.text = etCardExpiryDate.text.toString()
            tvCardCVV.text = etCardCVV.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateCardData(
        id:String,
        no:String,
        name:String,
        expirydate:String,
        cvv:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Cards").child(id)
        val cardInfo = CardModel(id, no, name, expirydate, cvv)
        dbRef.setValue(cardInfo)

    }
}