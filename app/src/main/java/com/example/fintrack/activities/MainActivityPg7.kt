package com.example.fintrack.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.adapters.AdapterTransactionClass
import com.example.fintrack.R
import com.example.fintrack.models.Transaction

class MainActivityPg7 : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var transList: ArrayList<Transaction>
    lateinit var imageList:Array<Int>
    lateinit var titleList:Array<String>
    lateinit var title2List:Array<String>
    lateinit var transAmount:Array<String>
    lateinit var transDate:Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pg7)


        imageList = arrayOf(
            R.drawable.netflix_logo,
            R.drawable.keells_logo,
            R.drawable.dialog_logo,
            R.drawable.instagram_logo,
            R.drawable.img_1,
        )

        titleList = arrayOf(
            "Netflix",
            "Keels",
            "Dialog",
            "Instagram",
            "ATM withdrawal"
        )

        title2List = arrayOf(
            "Entertainment",
            "Grocery & Shopping",
            "Telecommunication",
            "Social",
            "Cash withdrawal"
        )

        transAmount = arrayOf(
            "-Rs 1200",
            "-Rs 8600",
            "-Rs 5620",
            "-Rs 800",
            "+Rs 100000"
        )

        transDate = arrayOf(
            "28/02/2023",
            "29/02/2023",
            "21/02/2023",
            "3/02/2023",
            "8/02/2023"
        )

        recyclerView = findViewById(R.id.recyclerViewTP)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        transList = arrayListOf<Transaction>()
        getTrans()
    }
    private fun getTrans(){
        for(i in imageList.indices){
            val transClass = Transaction(imageList[i], titleList[i], title2List[i], transAmount[i], transDate[i])
            transList.add(transClass)
        }
        recyclerView.adapter = AdapterTransactionClass(transList)
    }
}