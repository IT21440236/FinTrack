package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.adapters.MemAdapter
import com.example.fintrack.models.MemberModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FetchingActivityUser : AppCompatActivity() {

    private lateinit var memRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var memList: ArrayList<MemberModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching_user)

        memRecyclerView = findViewById(R.id.rvMem)
        memRecyclerView.layoutManager = LinearLayoutManager(this)
        memRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        memList = arrayListOf<MemberModel>()

        getMembersData()
    }

    private fun getMembersData() {
        memRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Members")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                memList.clear()
                if(snapshot.exists()) {
                    for (memSnap in snapshot.children) {
                        val memData = memSnap.getValue(MemberModel::class.java)
                        memList.add(memData!!)
                    }
                    val mAdapter = MemAdapter(memList)
                    memRecyclerView.adapter = mAdapter


                    mAdapter.setOnItemClickListner(object : MemAdapter.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchingActivityUser, MemberDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("memId",memList[position].memId)
                            intent.putExtra("MemberName",memList[position].MemberName)
                            intent.putExtra("MemberEmail",memList[position].MemberEmail)
                            intent.putExtra("MemberOcc",memList[position].MemberOcc)
                            intent.putExtra("MemberWork",memList[position].MemberWork)
                            startActivity(intent)
                        }

                    })

                    memRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}