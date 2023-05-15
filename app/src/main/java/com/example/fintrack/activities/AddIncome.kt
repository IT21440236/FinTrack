package com.example.fintrack.activities

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import com.example.fintrack.models.IncomeModel
import com.example.fintrack.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class AddIncome : AppCompatActivity() {

    private lateinit var etnameAIP: EditText
    private lateinit var spcategoryAIP: Spinner
    private lateinit var etamountAIP: EditText
    private lateinit var etdateAIP: EditText
    private lateinit var btnaddAIP: AppCompatButton

    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_income)


        val imgbackwardnavAI = findViewById<ImageView>(R.id.imgbackwardnavAI)


        imgbackwardnavAI.setOnClickListener{
            val intent= Intent(this, Income::class.java)
            startActivity(intent)
        }
        val imgHomeiconAI = findViewById<ImageView>(R.id.imgHomeiconAI)


        imgHomeiconAI.setOnClickListener{
            val intent=Intent(this, Home::class.java)
            startActivity(intent)
        }

        val imgWalleticonAI = findViewById<ImageView>(R.id.imgWalleticonAI)


        imgWalleticonAI.setOnClickListener{
            val intent=Intent(this, WalletActivity::class.java)
            startActivity(intent)
        }

        val imgUsericonAI = findViewById<ImageView>(R.id.imgUsericonAI)


        imgUsericonAI.setOnClickListener{
            val intent=Intent(this, UserActivity::class.java)
            startActivity(intent)
        }


        etnameAIP =  findViewById(R.id.etnameAIP)
        spcategoryAIP =  findViewById(R.id.spcategoryAIP)

        val categories = resources.getStringArray(R.array.categoryincome)

        // Create an ArrayAdapter using the categories array and a default spinner layout
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        spcategoryAIP.adapter = adapter



        etamountAIP =  findViewById(R.id.etamountAIP)
        etdateAIP =  findViewById(R.id.etdateAIP)
        btnaddAIP =  findViewById(R.id.btnaddAIP)

        dbRef = FirebaseDatabase.getInstance().getReference("Incomes")

        etdateAIP.setOnClickListener {
            showDatePicker()
        }

        btnaddAIP.setOnClickListener{
            saveIncomeData()
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
                etdateAIP.setText(selectedDate)
            },
            year,
            month,
            day
        )

        // Set the maximum date to today's date
        datePicker.datePicker.maxDate = System.currentTimeMillis()

        datePicker.show()
    }


    private fun saveIncomeData() {


        //getting bvalues

        val incName = etnameAIP.text.toString()
        val incCategory = spcategoryAIP.selectedItem.toString()
        val incAmount = etamountAIP.text.toString()
        val incDate = etdateAIP.text.toString()

        if (incName.isEmpty()) {
            etnameAIP.error = "Please enter Expense name"
            return
        }
//        if (incCategory.isEmpty()) {
//            spcategoryAIP.error("Please select a category")
//        }
        if (incAmount.isEmpty()) {
            etamountAIP.error = "Please enter an amount"
            return
        }
        if (incDate.isEmpty()) {
            etdateAIP.error = "Please select a date"
            return
        }

        val incId = dbRef.push().key!!

        val income = IncomeModel(incId, incName, incCategory, incAmount, incDate )

        dbRef.child(incId).setValue(income)
            .addOnCompleteListener {
                Toast.makeText(this,"Data inserted successfully", Toast.LENGTH_LONG).show()

                etnameAIP.text.clear()
                spcategoryAIP.setSelection(0)
                etamountAIP.text.clear()
                etdateAIP.text.clear()

            }.addOnFailureListener{ err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}
