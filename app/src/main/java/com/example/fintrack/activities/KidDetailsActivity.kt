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
import com.example.fintrack.models.KidModel
import com.example.fintrack.models.MemberModel
import com.google.firebase.database.FirebaseDatabase

class KidDetailsActivity : AppCompatActivity() {

    private lateinit var tv_namePg36: TextView
    private lateinit var tv_agePg36: TextView
    private lateinit var tv_gradePg36: TextView
    private lateinit var tv_schoolPg36: TextView
    private lateinit var btn_updatePg36: Button
    private lateinit var btn_deletePg36: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kid_details)

        initView()
        setValuesToViews()

        btn_updatePg36.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("kidId").toString(),
                intent.getStringExtra("KidName").toString()
            )
        }

        btn_deletePg36.setOnClickListener {
            deleteRecordKid(
                intent.getStringExtra("kidId").toString()
            )
        }
    }

    private fun setValuesToViews() {

        tv_namePg36.text = intent.getStringExtra("KidName")
        tv_agePg36.text = intent.getStringExtra("KidAge")
        tv_gradePg36.text = intent.getStringExtra("KidGrade")
        tv_schoolPg36.text = intent.getStringExtra("KidSchool")
    }

    private fun initView() {

        tv_namePg36 = findViewById(R.id.tv_namePg36)
        tv_agePg36 = findViewById(R.id.tv_agePg36)
        tv_gradePg36 = findViewById(R.id.tv_gradePg36)
        tv_schoolPg36 = findViewById(R.id.tv_schoolPg36)

        btn_updatePg36 = findViewById(R.id.btn_updatePg36)
        btn_deletePg36 = findViewById(R.id.btn_deletePg36)
    }

    private fun openUpdateDialog(
        kidId: String,
        KidName: String
    ){
        val kDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val kDialogView = inflater.inflate(R.layout.update_dialog_kid, null)

        kDialog.setView(kDialogView)

        val etKidNameD = kDialogView.findViewById<EditText>(R.id.etKidNameD)
        val etKidAgeD = kDialogView.findViewById<EditText>(R.id.etKidAgeD)
        val etKidGradeD = kDialogView.findViewById<EditText>(R.id.etKidGradeD)
        val etKidSchoolD = kDialogView.findViewById<EditText>(R.id.etKidSchoolD)
        val btnUpdateDataDU = kDialogView.findViewById<Button>(R.id.btnUpdateDataDU)

        etKidNameD.setText(intent.getStringExtra("KidName").toString())
        etKidAgeD.setText(intent.getStringExtra("KidAge").toString())
        etKidGradeD.setText(intent.getStringExtra("KidGrade").toString())
        etKidSchoolD.setText(intent.getStringExtra("KidSchool").toString())

        kDialog.setTitle("Updating $KidName Record")

        val alertDialog = kDialog.create()
        alertDialog.show()

        btnUpdateDataDU.setOnClickListener {
            updateKidData(
                kidId,
                etKidNameD.text.toString(),
                etKidAgeD.text.toString(),
                etKidGradeD.text.toString(),
                etKidSchoolD.text.toString()
            )
            Toast.makeText(applicationContext, "Kid Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            tv_namePg36.text = etKidNameD.text.toString()
            tv_agePg36.text = etKidAgeD.text.toString()
            tv_gradePg36.text = etKidGradeD.text.toString()
            tv_schoolPg36.text = etKidSchoolD.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateKidData(
        kid_id: String,
        kid_name: String,
        kid_age: String,
        kid_grade: String,
        kid_school: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Kids").child(kid_id)
        val kidInfo = KidModel(kid_id, kid_name, kid_age, kid_grade, kid_school)
        dbRef.setValue(kidInfo)
    }

    private fun deleteRecordKid(
        kid_id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Kids").child(kid_id)
        val kTask = dbRef.removeValue()

        kTask.addOnSuccessListener {
            Toast.makeText(this, "Kid data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, FetchingActivityKid::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
}