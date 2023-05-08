package com.example.fintrack.activities

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import com.example.fintrack.models.ExpenseModel
import com.example.fintrack.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class AddExpense : AppCompatActivity() {

    private lateinit var etnameAEP: EditText
    private lateinit var spcategoryAEP: Spinner
    private lateinit var etamountAEP: EditText
    private lateinit var etdateAEP: EditText
    private lateinit var btnaddAEP: AppCompatButton

    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)


        val imgbackwardnavAE = findViewById<ImageView>(R.id.imgbackwardnavAE)


        imgbackwardnavAE.setOnClickListener{
            val intent= Intent(this, Expense::class.java)
            startActivity(intent)
        }




        etnameAEP =  findViewById(R.id.etnameAEP)
        spcategoryAEP =  findViewById(R.id.spcategoryAEP)

        val categories = resources.getStringArray(R.array.categoryexpense)

        // Create an ArrayAdapter using the categories array and a default spinner layout
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        spcategoryAEP.adapter = adapter



        etamountAEP =  findViewById(R.id.etamountAEP)
        etdateAEP =  findViewById(R.id.etdateAEP)
        btnaddAEP =  findViewById(R.id.btnaddAEP)

        dbRef = FirebaseDatabase.getInstance().getReference("Expenses")

        etdateAEP.setOnClickListener {
            showDatePicker()
        }


        btnaddAEP.setOnClickListener{
            saveExpenseData()
        }
    }

    private fun showDatePicker() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                // Update the EditText with the selected date
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                etdateAEP.setText(selectedDate)
            },
            year,
            month,
            day
        )

        // Set the maximum date to today's date
        datePicker.datePicker.maxDate = System.currentTimeMillis()

        datePicker.show()
    }






    private fun saveExpenseData() {


        //getting bvalues

        val expName = etnameAEP.text.toString()
        val expCategory = spcategoryAEP.selectedItem.toString()
        val expAmount = etamountAEP.text.toString()
        val expDate = etdateAEP.text.toString()

        if (expName.isEmpty()) {
            etnameAEP.error = "Please enter Expense name"

        }
//        if (expCategory.isEmpty()) {
//            spcategoryAEP.setError("Please select a category")
//        }
        if (expAmount.isEmpty()) {
            etamountAEP.error = "Please enter an amount"

        }
        if (expDate.isEmpty()) {
            etdateAEP.error = "Please select a date"

        }

        val expId = dbRef.push().key!!

        val expense = ExpenseModel(expId, expName, expCategory, expAmount, expDate )

        dbRef.child(expId).setValue(expense)
            .addOnCompleteListener {
                Toast.makeText(this,"Data inserted successfully", Toast.LENGTH_LONG).show()

                etnameAEP.text.clear()
                spcategoryAEP.setSelection(0)
                etamountAEP.text.clear()
                etdateAEP.text.clear()

            }.addOnFailureListener{ err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}