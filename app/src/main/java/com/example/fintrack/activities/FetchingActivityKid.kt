package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.adapters.KidAdapter
import com.example.fintrack.adapters.MemAdapter
import com.example.fintrack.models.KidModel
import com.example.fintrack.models.MemberModel
import com.google.firebase.database.*

class FetchingActivityKid : AppCompatActivity() {

    private lateinit var kidRecyclerView: RecyclerView
    private lateinit var tvLoadingDataK: TextView
    private lateinit var kidList: ArrayList<KidModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching_kid)

        kidRecyclerView = findViewById(R.id.rvKid)
        kidRecyclerView.layoutManager = LinearLayoutManager(this)
        kidRecyclerView.setHasFixedSize(true)
        tvLoadingDataK = findViewById(R.id.tvLoadingDataK)

        kidList = arrayListOf<KidModel>()

        getKidsData()
    }

    private fun getKidsData() {
        kidRecyclerView.visibility = View.GONE
        tvLoadingDataK.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Kids")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                kidList.clear()
                if(snapshot.exists()) {
                    for (kidSnap in snapshot.children) {
                        val kidData = kidSnap.getValue(KidModel::class.java)
                        kidList.add(kidData!!)
                    }
                    val kAdapter = KidAdapter(kidList)
                    kidRecyclerView.adapter = kAdapter

                    kAdapter.setOnItemClickListner(object : KidAdapter.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchingActivityKid, KidDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("kidId",kidList[position].kidId)
                            intent.putExtra("KidName",kidList[position].KidName)
                            intent.putExtra("KidAge",kidList[position].KidAge)
                            intent.putExtra("KidGrade",kidList[position].KidGrade)
                            intent.putExtra("KidSchool",kidList[position].KidSchool)
                            startActivity(intent)
                        }

                    })

                    kidRecyclerView.visibility = View.VISIBLE
                    tvLoadingDataK.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}