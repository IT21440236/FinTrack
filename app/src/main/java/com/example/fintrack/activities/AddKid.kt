package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.fintrack.R
import com.example.fintrack.models.KidModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddKid : AppCompatActivity() {

    private lateinit var edt_namePg35: EditText
    private lateinit var edt_agePg35: EditText
    private lateinit var edt_gradePg35: EditText
    private lateinit var edt_schoolPg35: EditText
    private lateinit var btn_addPg35: Button

    private lateinit var dbRef: DatabaseReference

    private lateinit var btnFetchDataKid: Button
    private lateinit var ImgVArrowPg35: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_kid)

        edt_namePg35 = findViewById(R.id.edt_namePg35)
        edt_agePg35 = findViewById(R.id.edt_agePg35)
        edt_gradePg35 = findViewById(R.id.edt_gradePg35)
        edt_schoolPg35 = findViewById(R.id.edt_schoolPg35)
        btn_addPg35 = findViewById(R.id.btn_addPg35)

        btnFetchDataKid = findViewById(R.id.btnFetchDataKid)
        ImgVArrowPg35 = findViewById(R.id.ImgVArrowPg35)

        btnFetchDataKid.setOnClickListener {
            val intent = Intent(this, FetchingActivityKid::class.java)
            startActivity(intent)
        }
        ImgVArrowPg35.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        dbRef = FirebaseDatabase.getInstance().getReference("Kids")

        btn_addPg35.setOnClickListener{
            saveKidData()
        }
    }

    private fun saveKidData(){

        //getting values
        val kidName = edt_namePg35.text.toString()
        val KidAge = edt_agePg35.text.toString()
        val KidGrade = edt_gradePg35.text.toString()
        val KidSchool = edt_schoolPg35.text.toString()

        if(kidName.isEmpty()) {
            edt_namePg35.error = "please enter name"
        }

        if(KidAge.isEmpty()) {
            edt_agePg35.error = "please enter email"
        }

        if(KidGrade.isEmpty()) {
            edt_gradePg35.error = "please enter occupation"
        }

        if(KidSchool.isEmpty()) {
            edt_schoolPg35.error = "please enter workplace"
        }

        val kidId = dbRef.push().key!!

        val member = KidModel(kidId, kidName, KidAge, KidGrade, KidSchool)

        dbRef.child(kidId).setValue(member)
            .addOnCompleteListener{
                Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_LONG).show()

                edt_namePg35.text.clear()
                edt_agePg35.text.clear()
                edt_gradePg35.text.clear()
                edt_schoolPg35.text.clear()

            }.addOnFailureListener{err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }

}
