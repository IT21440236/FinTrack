package com.example.fintrack.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.fintrack.R
import com.example.fintrack.models.GoalModel
import com.google.firebase.database.FirebaseDatabase

class UpdateGoalActivity : AppCompatActivity() {

    private lateinit var tvGoal: TextView
    private lateinit var tvpaymenttime: TextView
    private lateinit var tvAmount: TextView
    private lateinit var btnUpdate:Button
    private lateinit var btnDelete:Button
    
    private lateinit var btnBack: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_goal)

        tvGoal = findViewById(R.id.etFinancialGoalUGP)
        tvpaymenttime = findViewById(R.id.etTextView20)
        tvAmount = findViewById(R.id.etAmountUGP)
        btnUpdate = findViewById(R.id.button1)
        btnDelete = findViewById(R.id.btnAddAP)
        
        btnBack=findViewById<ImageView>(R.id.btnBackAP)

        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("goalId").toString(),
                intent.getStringExtra("financialGoal").toString(),
                intent.getStringExtra("paymenttime").toString(),
                intent.getStringExtra("amount").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteGoalRecord(
                intent.getStringExtra("goalId").toString()
            )
        }
        
        btnBack.setOnClickListener {
            startActivity(Intent(this@UpdateGoalActivity, GoalActivity::class.java))
        }

    }

    //create function delete goal record
    private fun deleteGoalRecord(
        id:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Goal").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Goal data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, GoalActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }


    //create function for set views
    private fun setValuesToViews(){
        intent.getStringExtra(("goalId"))
        tvGoal.text = intent.getStringExtra("financialGoal")
        tvpaymenttime.text = intent.getStringExtra("paymenttime")
        tvAmount.text = intent.getStringExtra("amount")
    }

    private fun openUpdateDialog(
        goalId:String,
        finacialgoal:String,
        paymenttime:String,
        amount:String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_update_dialog_goal, null)

        mDialog.setView(mDialogView)

        val etFinancialGoalDGP = mDialogView.findViewById<EditText>(R.id.etFinancialGoalDGP)
        val etTimeDGP = mDialogView.findViewById<EditText>(R.id.etTimeDGP)
        val etAmountDGP = mDialogView.findViewById<EditText>(R.id.etAmountDGP)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etFinancialGoalDGP.setText(intent.getStringExtra("financialGoal")).toString()
        etTimeDGP.setText(intent.getStringExtra("paymenttime")).toString()
        etAmountDGP.setText(intent.getStringExtra("amount")).toString()

        mDialog.setTitle("Updating $etFinancialGoalDGP Record" )

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateGoal(
                goalId,
                etFinancialGoalDGP.text.toString(),
                etTimeDGP.text.toString(),
                etAmountDGP.text.toString()
            )

            Toast.makeText(applicationContext, "Goal Data Updated", Toast.LENGTH_LONG).show()

            tvGoal.text = etFinancialGoalDGP.text.toString()
            tvpaymenttime.text = etTimeDGP.text.toString()
            tvAmount.text = etAmountDGP.text.toString()

            alertDialog.dismiss()
        }
    }

    //create function update goal
    private fun updateGoal(
        id:String,
        finacialgoal: String,
        time:String,
        amount:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Goal").child(id)
        val goalInfo = GoalModel(id, finacialgoal, time, amount)
        dbRef.setValue(goalInfo)
    }

}
