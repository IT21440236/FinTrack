package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.fintrack.models.MemberModel
import com.example.fintrack.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddUser : AppCompatActivity() {

    private lateinit var edt_unPg31: EditText
    private lateinit var edt_mailPg31: EditText
    private lateinit var edt_occPg31: EditText
    private lateinit var edt_workplacePg31: EditText
    private lateinit var btn_addPg31: Button

    private lateinit var btnFetchDataUserP: Button
    private lateinit var ImgVArrowPg31: ImageView

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        edt_unPg31 = findViewById(R.id.edt_unPg31)
        edt_mailPg31 = findViewById(R.id.edt_mailPg31)
        edt_occPg31 = findViewById(R.id.edt_occPg31)
        edt_workplacePg31 = findViewById(R.id.edt_workplacePg31)
        btn_addPg31 = findViewById(R.id.btn_addPg31)

        btnFetchDataUserP = findViewById(R.id.btnFetchDataUserP)
        ImgVArrowPg31 = findViewById(R.id.ImgVArrowPg31)

        btnFetchDataUserP.setOnClickListener {
            val intent = Intent(this, FetchingActivityUser::class.java)
            startActivity(intent)
        }
        ImgVArrowPg31.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        dbRef = FirebaseDatabase.getInstance().getReference("Members")

        btn_addPg31.setOnClickListener{
            saveMemberData()
        }
    }

    private fun saveMemberData(){

        //getting values
        val memName = edt_unPg31.text.toString()
        val memEmail = edt_mailPg31.text.toString()
        val memOcc = edt_occPg31.text.toString()
        val memWork = edt_workplacePg31.text.toString()

        if(memName.isEmpty()) {
            edt_unPg31.error = "please enter name"
        }

        if(memEmail.isEmpty()|| !isValidEmail(memEmail)) {
            edt_mailPg31.error = "please enter a valid email"
            return
        }

        if(memOcc.isEmpty()) {
            edt_occPg31.error = "please enter occupation"
        }

        if(memWork.isEmpty()) {
            edt_workplacePg31.error = "please enter workplace"
        }

        val memId = dbRef.push().key!!

        val member = MemberModel(memId, memName, memEmail, memOcc, memWork)

        dbRef.child(memId).setValue(member)
            .addOnCompleteListener{
                Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_LONG).show()

                edt_unPg31.text.clear()
                edt_mailPg31.text.clear()
                edt_occPg31.text.clear()
                edt_workplacePg31.text.clear()

            }.addOnFailureListener{err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}