package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.adapters.ExpAdapter
import com.example.fintrack.models.ExpenseModel
import com.google.firebase.database.*

class Expense : AppCompatActivity() {

    private lateinit var expRecyclerView: RecyclerView
    private lateinit var lbLoadingEP: TextView
    private lateinit var expList: ArrayList<ExpenseModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense)


       val ivAddiconblueEP = findViewById<ImageView>(R.id.ivAddiconblueEP)


        ivAddiconblueEP.setOnClickListener{
            val intent=Intent(this, AddExpense::class.java)
            startActivity(intent)
        }


        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        expRecyclerView = findViewById(R.id.rvExpensesEP)
        expRecyclerView.layoutManager = LinearLayoutManager(this)
        expRecyclerView.setHasFixedSize(true)
        lbLoadingEP =  findViewById(R.id.lbLoadingEP)

        expList = arrayListOf<ExpenseModel>()

        getExpenseData()



    }

    private fun getExpenseData(){

        expRecyclerView.visibility = View.GONE
        lbLoadingEP.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Expenses")


        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                expList.clear()
                if(snapshot.exists()){
                    for(expSnap in snapshot.children) {
                        val expData = expSnap.getValue(ExpenseModel::class.java)
                        expList.add(expData!!)
                    }
                    val mAdapter = ExpAdapter(expList)
                    expRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : ExpAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@Expense,UpdateExpense::class.java)

                            //put extras
                            intent.putExtra("expId", expList[position].expId)
                            intent.putExtra("expName", expList[position].expName)
                            intent.putExtra("expCategory", expList[position].expCategory)
                            intent.putExtra("expAmount", expList[position].expAmount)
                            intent.putExtra("expDate", expList[position].expDate)
                            startActivity(intent)
                        }

                    })

                    expRecyclerView.visibility = View.VISIBLE
                    lbLoadingEP.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })



    }
}