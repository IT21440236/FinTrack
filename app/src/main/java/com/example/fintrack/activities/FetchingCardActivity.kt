package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.adapters.CardAdapter
import com.example.fintrack.models.CardModel
import com.google.firebase.database.*

class FetchingCardActivity : AppCompatActivity() {

    private lateinit var cardRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var cardList: ArrayList<CardModel>
    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching_card)

        cardRecyclerView = findViewById(R.id.rvCard)
        cardRecyclerView.layoutManager = LinearLayoutManager(this)
        cardRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        cardList = arrayListOf<CardModel>()

        getCardData()
    }

    private  fun getCardData(){
        cardRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Cards")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                cardList.clear()
                if (snapshot.exists()){
                    for (cardSnap in snapshot.children){
                        val cardData = cardSnap.getValue(CardModel::class.java)
                        cardList.add(cardData!!)
                    }
                    val cAdapter = CardAdapter(cardList)
                    cardRecyclerView.adapter = cAdapter

                    cAdapter.setOnItemClickListener(object : CardAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchingCardActivity, CardDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("cardId", cardList[position].cardId)
                            intent.putExtra("cardNo", cardList[position].cardNo)
                            intent.putExtra("cardName", cardList[position].cardName)
                            intent.putExtra("cardExpiryDate", cardList[position].cardExpiryDate)
                            intent.putExtra("cardCVV", cardList[position].cardCVV)
                            startActivity(intent)
                        }
                    })

                    cardRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE


                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}