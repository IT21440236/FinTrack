package com.example.fintrack.activities
//
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.adapters.GoalAdapter
import com.example.fintrack.models.GoalModel
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class GoalActivity : AppCompatActivity() {

    private lateinit var plusbtn: Button

    private lateinit var goalRecyclerView: RecyclerView
    private lateinit var goalList:ArrayList<GoalModel>
    private lateinit var dbRef: DatabaseReference

    private lateinit var searchView: SearchView

    //private lateinit var adapter: GoalAdapter
    //private var amount:Double = 0.0

    //private lateinit var etAmount :EditText
    private lateinit var btnCheck:Button
    //private lateinit var btnBack:Button
    private lateinit var btnHome: ImageButton
    private lateinit var btnUser:ImageButton
    private lateinit var btnWallet:ImageButton
    private lateinit var btnBack:ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)

        plusbtn = findViewById(R.id.btnAddGP)

        searchView = findViewById(R.id.searchViewGP)

        //etAmount = findViewById(R.id.etGoalAmount)
        btnCheck = findViewById(R.id.btnCheck)
        //btnBack = findViewById(R.id.btnBackAP)
        //btnHome = findViewById(R.id.btnHomeUP)
        
        btnUser=findViewById<ImageButton>(R.id.btnUserAP)
        btnWallet=findViewById<ImageButton>(R.id.btnWalletAP)
        btnBack=findViewById<ImageView>(R.id.btnBackAP)

        btnBack.setOnClickListener {
            startActivity(Intent(this@GoalActivity, UserActivity::class.java))
        }

        plusbtn.setOnClickListener {
            startActivity(Intent(this@GoalActivity, AddgoalActivity::class.java))
            finish()
        }

        goalList = arrayListOf<GoalModel>()

        goalRecyclerView = findViewById(R.id.recyclerViewGP)
        goalRecyclerView.layoutManager = LinearLayoutManager(this)
        goalRecyclerView.setHasFixedSize(true)
        //getGoalData()


        //adapter = GoalAdapter(goalList)
        //goalRecyclerView.adapter = adapter

//        getGoalData()

        //goalList = arrayListOf<GoalModel>()


        //getGoalData()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                if (newText != null) {
//                    filterList(newText.toLowerCase(Locale.getDefault()))
//                }
//                //filterList(newText)
//                return true

                filter(newText)
                goalRecyclerView.adapter?.notifyDataSetChanged()
                return true
            }

        })

        getGoalData()



        btnCheck.setOnClickListener {
//            val res1 = etAmount.text.toString().toInt()
//
//            decrease(75000, res1)

            startActivity(Intent(this@GoalActivity, GoalReco::class.java))
        }
        
        btnUser.setOnClickListener {
            startActivity(Intent(this@GoalActivity, UserActivity::class.java))
        }

        btnWallet.setOnClickListener {
            startActivity(Intent(this@GoalActivity, WalletActivity::class.java))
        }

        btnBack.setOnClickListener {
            startActivity(Intent(this@GoalActivity, UserActivity::class.java))
        }



    }

//     private fun decrease(res1 : Int, res2 : Int){
//         val result = res1 - res2

//         Toast.makeText(this, "Remain amount Rs.${result} ", Toast.LENGTH_LONG).show()
//     }

//    private fun filterList(query: String?){
////        if(query != null){
////            val filteredList = ArrayList<GoalModel>()
////            for(i in goalList){
////                if(i.financialGoal!!.lowercase().contains(query)){
////                    filteredList.add(i)
////                }
////            }
////
////            if(filteredList.isEmpty()){
////                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
////            }else{
////                adapter.setFilteredList(filteredList)
//////                val mAdapter = GoalAdapter(filteredList)
//////                goalRecyclerView.adapter = mAdapter
////
////            }
////        }
//
//        if(query != null) {
//            val filteredList = ArrayList<GoalModel>()
//            for (item in goalList) {
//                if (item.financialGoal?.toLowerCase(Locale.getDefault())?.contains(query) == true) {
//                    filteredList.add(item)
//                }
//            }
//            if (filteredList.isNotEmpty()) {
//                adapter.setFilteredList(filteredList)
//            } else {
//                adapter.setFilteredList(goalList)
//                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//
//
//
////        val filterList = ArrayList<GoalModel>()
////
////        if(!query.isNullOrBlank()){
////            val searchQuery = query.toLowerCase(Locale.ROOT).trim()
////
////            for(goal in goalList){
////                if(goal.financialGoal!!.toLowerCase(Locale.ROOT).contains(searchQuery)){
////                    filterList.add(goal)
////                }
////            }
////        }else{
////            filterList.addAll(goalList)
////        }
////
////        val mAdapter = GoalAdapter(filterList)
////        goalRecyclerView.adapter = mAdapter
//    }

//search
    private fun filter(text:String?){
        text?.let{
            val filteredList=goalList.filter { goal->
                goal.financialGoal!!.contains(text,ignoreCase = true)
            }.toMutableList()
            (goalRecyclerView.adapter as GoalAdapter).submitList(filteredList)
        }?: run{
            getGoalData()
        }
    }

    //fetch goal data
    private fun getGoalData(){
        goalRecyclerView.visibility = View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("Goal")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                goalList.clear()

                if(snapshot.exists()){
                    for(goalsnap in snapshot.children){
                        val goalData = goalsnap.getValue(GoalModel::class.java)
                        goalList.add(goalData!!)
                    }
                    val mAdapter = GoalAdapter(goalList)
                    goalRecyclerView.adapter = mAdapter

                    mAdapter.setOnClickListner(object : GoalAdapter.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@GoalActivity, UpdateGoalActivity::class.java)

                            //put extras
                            intent.putExtra("goalId", goalList[position].goalId)
                            intent.putExtra("financialGoal", goalList[position].financialGoal)
                            intent.putExtra("paymenttime", goalList[position].paymenttime)
                            intent.putExtra("amount", goalList[position].amount)

                            startActivity(intent)
                        }

                    })

                    goalRecyclerView.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
