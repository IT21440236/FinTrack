package com.example.fintrackp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrackp.R
import com.example.fintrackp.adapters.GoalAdapter
import com.example.fintrackp.models.GoalModel
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class GoalActivity : AppCompatActivity() {

    private lateinit var plusbtn: Button

    private lateinit var goalRecyclerView: RecyclerView
    private lateinit var goalList:ArrayList<GoalModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)

        plusbtn = findViewById(R.id.btnAddGP)

        plusbtn.setOnClickListener {
            startActivity(Intent(this@GoalActivity, AddgoalActivity::class.java))
            finish()
        }

        goalRecyclerView = findViewById(R.id.recyclerViewGP)
        goalRecyclerView.layoutManager = LinearLayoutManager(this)
        goalRecyclerView.setHasFixedSize(true)

        goalList = arrayListOf<GoalModel>()

        getGoalData()



    }

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