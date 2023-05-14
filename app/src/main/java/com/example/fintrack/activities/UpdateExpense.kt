package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import com.example.fintrack.R
import com.example.fintrack.models.ExpenseModel
import com.example.fintrack.models.IncomeModel
import com.google.firebase.database.FirebaseDatabase

class UpdateExpense : AppCompatActivity() {

    private lateinit var etNameUE: EditText
    private lateinit var spnCategoryUE: Spinner
    private lateinit var etAmountUE: EditText
    private lateinit var etDateUE: EditText
    private lateinit var btnUpdateUE: AppCompatButton
    private lateinit var btnDeleteUE: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_expense)


        val ivbackwardnavUE = findViewById<ImageView>(R.id.ivbackwardnavUE)


        ivbackwardnavUE.setOnClickListener{
            val intent= Intent(this, Expense::class.java)
            startActivity(intent)
        }
         val imgHomeiconUE = findViewById<ImageView>(R.id.imgHomeiconUE)


        imgHomeiconUE.setOnClickListener{
            val intent=Intent(this, Home::class.java)
            startActivity(intent)
        }

        val imgWalletUE = findViewById<ImageView>(R.id.imgWalletUE)


        imgWalletUE.setOnClickListener{
            val intent=Intent(this, WalletActivity::class.java)
            startActivity(intent)
        }

        val imgUserUE = findViewById<ImageView>(R.id.imgUserUE)


        imgUserUE.setOnClickListener{
            val intent=Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

        initView()
        setValuesToViews()

        btnUpdateUE.setOnClickListener {
            updateExpense()
        }

//        btnDeleteUE.setOnClickListener {
//            deleteRecord(
//                intent.getStringExtra("expId").toString()
//            )
//        }

        btnDeleteUE.setOnClickListener {
            deleteExpense()
        }

    }

//    private fun deleteRecord(
//        id:String
//    ){
//        val dbRef = FirebaseDatabase.getInstance().getReference("Expenses").child(id)
//        val mTask = dbRef.removeValue()
//
//        mTask.addOnSuccessListener {
//            Toast.makeText(this, "Expense data deleted", Toast.LENGTH_LONG).show()
//            val intent = Intent(this, Expense::class.java)
//            finish()
//            startActivity(intent)
//        }.addOnFailureListener{error ->
//            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
//        }
//    }

    private fun deleteExpense() {
        val dbRef = FirebaseDatabase.getInstance().getReference("Expenses").child(intent.getStringExtra("expId").toString())
        dbRef.removeValue()

        Toast.makeText(applicationContext, "Expense Data Deleted", Toast.LENGTH_LONG).show()
    }



    private fun updateExpense() {
        val dbRef = FirebaseDatabase.getInstance().getReference("Expenses").child(intent.getStringExtra("expId").toString())
        val expInfo = ExpenseModel(
            intent.getStringExtra("expId").toString(),
            etNameUE.text.toString(),
            spnCategoryUE.selectedItem.toString(),
            etAmountUE.text.toString(),
            etDateUE.text.toString(),

            )

        dbRef.setValue(expInfo)

        Toast.makeText(applicationContext, "Expense Data Updated", Toast.LENGTH_LONG).show()
    }

    private fun initView() {
        etNameUE = findViewById(R.id.etNameUE)
        spnCategoryUE = findViewById(R.id.spnCategoryUE)

        val categories = resources.getStringArray(R.array.categoryexpense)

        // Create an ArrayAdapter using the categories array and a default spinner layout
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        spnCategoryUE.adapter = adapter

        etAmountUE = findViewById(R.id.etAmountUE)
        etDateUE = findViewById(R.id.etDateUE)
        btnUpdateUE = findViewById(R.id.btnUpdateUE)
        btnDeleteUE = findViewById(R.id.btnDeleteUE)
    }

    private fun setValuesToViews(){

        etNameUE.setText(intent.getStringExtra("expName"))
        spnCategoryUE.setSelection(getIndex(spnCategoryUE, intent.getStringExtra("expCategory")))
        etAmountUE.setText(intent.getStringExtra("expAmount"))
        etDateUE.setText(intent.getStringExtra("expDate"))

    }

    private fun getIndex(spinner: Spinner, value: String?): Int {
        if (value == null) {
            return 0
        }
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i).toString() == value) {
                return i
            }
        }
        return 0
    }
}
