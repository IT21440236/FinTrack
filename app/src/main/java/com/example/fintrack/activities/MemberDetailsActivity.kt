package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.fintrack.R
import com.example.fintrack.models.MemberModel
import com.google.firebase.database.FirebaseDatabase

class MemberDetailsActivity : AppCompatActivity() {


    private lateinit var edtunPg32: TextView
    private lateinit var edtmailPg32: TextView
    private lateinit var edtpwdPg32: TextView
    private lateinit var edtworkplacePg32: TextView
    private lateinit var btn_updatePg32: Button
    private lateinit var btn_deletePg32: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_details)

        initView()
        setValuesToViews()

        btn_updatePg32.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("memId").toString(),
                intent.getStringExtra("MemberName").toString()
            )
        }

        btn_deletePg32.setOnClickListener {
            deleteRecordUser(
                intent.getStringExtra("memId").toString()
            )
        }

    }

    private fun initView() {

        edtunPg32 = findViewById(R.id.edtunPg32)
        edtmailPg32 = findViewById(R.id.edtmailPg32)
        edtpwdPg32 = findViewById(R.id.edtpwdPg32)
        edtworkplacePg32 = findViewById(R.id.edtworkplacePg32)

        btn_updatePg32 = findViewById(R.id.btn_updatePg32)
        btn_deletePg32 = findViewById(R.id.btn_deletePg32)
    }

    private fun setValuesToViews() {

        edtunPg32.text = intent.getStringExtra("MemberName")
        edtmailPg32.text = intent.getStringExtra("MemberEmail")
        edtpwdPg32.text = intent.getStringExtra("MemberOcc")
        edtworkplacePg32.text = intent.getStringExtra("MemberWork")
    }

    private fun openUpdateDialog(
        memId: String,
        MemberName: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog_user, null)

        mDialog.setView(mDialogView)

        val etMemNameD = mDialogView.findViewById<EditText>(R.id.etMemNameD)
        val etMemEmailD = mDialogView.findViewById<EditText>(R.id.etMemEmailD)
        val etMemOccD = mDialogView.findViewById<EditText>(R.id.etMemOccD)
        val etMemWorkD = mDialogView.findViewById<EditText>(R.id.etMemWorkD)

        val btnUpdateDataD = mDialogView.findViewById<Button>(R.id.btnUpdateDataD)

        etMemNameD.setText(intent.getStringExtra("MemberName").toString())
        etMemEmailD.setText(intent.getStringExtra("MemberEmail").toString())
        etMemOccD.setText(intent.getStringExtra("MemberOcc").toString())
        etMemWorkD.setText(intent.getStringExtra("MemberWork").toString())

        mDialog.setTitle("Updating $MemberName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateDataD.setOnClickListener {
            updateMemData(
                memId,
                etMemNameD.text.toString(),
                etMemEmailD.text.toString(),
                etMemOccD.text.toString(),
                etMemWorkD.text.toString()
            )
            Toast.makeText(applicationContext, "Member Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            edtunPg32.text = etMemNameD.text.toString()
            edtmailPg32.text = etMemEmailD.text.toString()
            edtpwdPg32.text = etMemOccD.text.toString()
            edtworkplacePg32.text = etMemWorkD.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateMemData(
        user_id: String,
        user_name: String,
        user_email: String,
        user_occ: String,
        user_work: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Members").child(user_id)
        val memInfo = MemberModel(user_id, user_name, user_email, user_occ, user_work)
        dbRef.setValue(memInfo)
    }

    private fun deleteRecordUser(
        user_id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Members").child(user_id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Member data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, FetchingActivityUser::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
}