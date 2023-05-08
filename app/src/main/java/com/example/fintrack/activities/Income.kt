package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.adapters.ExpAdapter
import com.example.fintrack.adapters.IncAdapter
import com.example.fintrack.models.ExpenseModel
import com.example.fintrack.models.IncomeModel
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_income.*

class Income : AppCompatActivity() {

    private lateinit var incRecyclerView: RecyclerView
    private lateinit var lbLoadingIP: TextView
    private lateinit var incList: ArrayList<IncomeModel>
    private lateinit var dbRef: DatabaseReference
  // private lateinit var lbsavingsvalIP: EditText

    private var totalIncome: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income)

//       val lbsavingsvalIP = findViewById<EditText>(R.id.lbsavingsvalIP)
//
//        lbsavingsvalIP.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//
//            override fun afterTextChanged(s: Editable?) {
//                // Update the total income based on the new value in lbsavingsvalIP
//                totalIncome = s.toString().toDoubleOrNull() ?: 0.0
//            }
//        })
        val ivAddiconblueIP = findViewById<ImageView>(R.id.ivAddiconblueIP)


        ivAddiconblueIP.setOnClickListener{
            val intent= Intent(this, AddIncome::class.java)
            startActivity(intent)
        }

        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()


        incRecyclerView = findViewById(R.id.rvIncomesIP)
        incRecyclerView.layoutManager = LinearLayoutManager(this)
        incRecyclerView.setHasFixedSize(true)
        lbLoadingIP =  findViewById(R.id.lbLoadingIP)
    //    lbsavingsvalIP = findViewById(R.id.lbsavingsvalIP)
        incList = arrayListOf<IncomeModel>()

        getIncomeData()
    }

    private fun getIncomeData(){

        incRecyclerView.visibility = View.GONE
        lbLoadingIP.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Incomes")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){

                incList.clear()
               // totalIncome = 0.0
                if(snapshot.exists()){
                    for(incSnap in snapshot.children) {
                        val incData = incSnap.getValue(IncomeModel::class.java)
                        incList.add(incData!!)

//                        val amount = incData!!.incAmount?.toDoubleOrNull()
//                        if (amount != null) {
//                            totalIncome += amount
//                        }
//
                    }
                    //lbsavingsvalIP.setText(totalIncome.toString())
                    val mAdapter = IncAdapter(incList)
                    incRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : IncAdapter.onItemClickListener{
                        override fun onItemClick(position: Int){
                            val intent = Intent(this@Income,UpdateIncome::class.java)


                            intent.putExtra("incId", incList[position].incId)
                            intent.putExtra("incName", incList[position].incName)
                            intent.putExtra("incCategory", incList[position].incCategory)
                            intent.putExtra("incAmount", incList[position].incAmount)
                            intent.putExtra("incDate", incList[position].incDate)
                            startActivity(intent)
                        }

                    })

                    incRecyclerView.visibility = View.VISIBLE
                    lbLoadingIP.visibility = View.GONE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}