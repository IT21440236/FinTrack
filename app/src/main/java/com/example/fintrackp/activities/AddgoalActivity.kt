package com.example.fintrackp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fintrackp.R
import com.example.fintrackp.models.GoalModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddgoalActivity : AppCompatActivity() {

    private lateinit var etGoal: EditText
    private lateinit var etpaymenttime: EditText
    private lateinit var etAmount: EditText
    private lateinit var btnGoalAdd: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addgoal)

        etGoal = findViewById(R.id.etFinancialGoalAP)
        etpaymenttime = findViewById(R.id.etPaymentTimeAGP)
        etAmount = findViewById(R.id.etAmountAP)
        btnGoalAdd = findViewById(R.id.btnAddAP)

        dbRef = FirebaseDatabase.getInstance().getReference("Goal")

        btnGoalAdd.setOnClickListener {
            saveGoalData()
        }
    }

    private fun saveGoalData(){
        //getting values
        val financialgoal = etGoal.text.toString()
        val paymentfor = etpaymenttime.text.toString()
        val amount = etAmount.text.toString()

        if(financialgoal.isEmpty()){
            etGoal.error = "Please enter financial goal"
        }
        if(paymentfor.isEmpty()){
            etpaymenttime.error = "Please enter financial goal"
        }
        if(amount.isEmpty()){
           etAmount.error = "Please enter financial goal"
        }

        val goalId = dbRef.push().key!!

        val goal = GoalModel(goalId, financialgoal, paymentfor, amount)

        dbRef.child(goalId).setValue(goal)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etGoal.text.clear()
                etpaymenttime.text.clear()
                etAmount.text.clear()

            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

}