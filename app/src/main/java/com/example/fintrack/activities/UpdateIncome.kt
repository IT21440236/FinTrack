package com.example.fintrack.activities
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.*
//import androidx.appcompat.widget.AppCompatButton
//import com.example.fintrack.R
//import com.example.fintrack.models.IncomeModel
//import com.google.firebase.database.FirebaseDatabase
//
//class UpdateIncome : AppCompatActivity() {
//
//    private lateinit var ednameUIP: EditText
//    private lateinit var spcategoryUIP: Spinner
//    private lateinit var etamountUIP: EditText
//    private lateinit var etDateUIP: EditText
//    private lateinit var btnUpdateUIP: AppCompatButton
//
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_update_income)
//
//
//        val imgBackwardUI = findViewById<ImageView>(R.id.imgBackwardUI)
//
//
//        imgBackwardUI.setOnClickListener{
//            val intent= Intent(this, Income::class.java)
//            startActivity(intent)
//        }
//
//        initView()
//        setValuesToViews()
//
//        btnUpdateUIP.setOnClickListener {
//            updateIncome()
//        }
//
//    }
//
//    private fun updateIncome(){
//        val dbRef = FirebaseDatabase.getInstance().getReference("Incomes").child(intent.getStringExtra("incId").toString())
//        val incInfo = IncomeModel(
//            intent.getStringExtra("incId").toString(),
//            ednameUIP.text.toString(),
//            spcategoryUIP.selectedItem.toString(),
//            etamountUIP.text.toString(),
//            etDateUIP.text.toString(),
//
//        )
//        dbRef.setValue(incInfo)
//
//        Toast.makeText(applicationContext, "Income Data Updated", Toast.LENGTH_LONG).show()
//    }
//
//    private fun initView(){
//        ednameUIP = findViewById(R.id.ednameUIP)
//        spcategoryUIP = findViewById(R.id. spcategoryUIP)
//
//        val categories = resources.getStringArray(R.array.categoryincome)
//
//        // Create an ArrayAdapter using the categories array and a default spinner layout
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
//
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//        // Apply the adapter to the spinner
//        spcategoryUIP.adapter = adapter
//
//        etamountUIP = findViewById(R.id.etamountUIP)
//        etDateUIP = findViewById(R.id.etDateUIP)
//        btnUpdateUIP = findViewById(R.id.btnUpdateUIP)
//    }
//
//    private fun setValuesToViews() {
//        ednameUIP.setText(intent.getStringExtra("incName"))
//        spcategoryUIP.setSelection(getIndex(spcategoryUIP, intent.getStringExtra("incCategory")))
//        etamountUIP.setText(intent.getStringExtra("incAmount"))
//        etDateUIP.setText(intent.getStringExtra("incDate"))
//    }
//
//    private fun getIndex(spinner: Spinner, value: String?): Int {
//        if (value == null) {
//            return 0
//        }
//        for (i in 0 until spinner.count) {
//            if (spinner.getItemAtPosition(i).toString() == value) {
//                return i
//            }
//        }
//        return 0
//    }
//}