package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.adapters.ExpAdapter
import com.example.fintrack.adapters.IncAdapter
import com.example.fintrack.models.ExpenseModel
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class Expense : AppCompatActivity() {

    private lateinit var expRecyclerView: RecyclerView
    private lateinit var lbLoadingEP: TextView
    private lateinit var expList: ArrayList<ExpenseModel>
    private lateinit var dbRef: DatabaseReference
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense)


       val ivAddiconblueEP = findViewById<ImageView>(R.id.ivAddiconblueEP)


        ivAddiconblueEP.setOnClickListener{
            val intent=Intent(this, AddExpense::class.java)
            startActivity(intent)
        }
         val imgHomeiconEP = findViewById<ImageView>(R.id.imgHomeiconEP)


        imgHomeiconEP.setOnClickListener{
            val intent=Intent(this, Home::class.java)
            startActivity(intent)
        }

        val imgWalleticonEP = findViewById<ImageView>(R.id.imgWalleticonEP)


        imgWalleticonEP.setOnClickListener{
            val intent=Intent(this, WalletActivity::class.java)
            startActivity(intent)
        }

        val imgUsericonEP = findViewById<ImageView>(R.id.imgUsericonEP)


        imgUsericonEP.setOnClickListener{
            val intent=Intent(this, UserActivity::class.java)
            startActivity(intent)
        }
        val imageView = findViewById<ImageView>(R.id.imageView)


        imageView.setOnClickListener{
            val intent=Intent(this, Home::class.java)
            startActivity(intent)
        }


        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        expRecyclerView = findViewById(R.id.rvExpensesEP)
        expRecyclerView.layoutManager = LinearLayoutManager(this)
        expRecyclerView.setHasFixedSize(true)
        lbLoadingEP =  findViewById(R.id.lbLoadingEP)
        searchView = findViewById(R.id.searchviewEP)

        expList = arrayListOf<ExpenseModel>()

        getExpenseData()

//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                filter(newText, selectedCategory) // Pass the selected category along with the search text
//                return true
//            }
//        })



        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return true
            }
        })

    }

//    private fun filter(text: String?, category: String?) {
//        text?.let { searchText ->
//            val filteredList = if (category.isNullOrEmpty()) {
//                // Filter by expense name only
//                expList.filter { expense ->
//                    expense.expName?.toLowerCase(Locale.ROOT)?.contains(searchText.toLowerCase(Locale.ROOT)) == true
//                }
//            } else {
//                // Filter by expense name and category
//                expList.filter { expense ->
//                    val matchesSearchText = expense.expName?.toLowerCase(Locale.ROOT)?.contains(searchText.toLowerCase(Locale.ROOT)) == true
//                    val matchesCategory = expense.expCategory?.toLowerCase(Locale.ROOT)?.contains(category.toLowerCase(Locale.ROOT)) == true
//                    matchesSearchText && matchesCategory
//                }
//            }
//            (expRecyclerView.adapter as ExpAdapter).submitList(filteredList)
//        } ?: run {
//            (expRecyclerView.adapter as ExpAdapter).submitList(expList)
//        }
//    }


    private fun filter(text: String?) {
        text?.let {
            val filteredList = expList.filter { expense ->
                expense.expName?.toLowerCase(Locale.ROOT)?.contains(text.toLowerCase(Locale.ROOT)) == true
            }
            (expRecyclerView.adapter as ExpAdapter).submitList(filteredList)
        } ?: run {
            (expRecyclerView.adapter as ExpAdapter).submitList(expList)
        }
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
