package com.example.fintrack.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.fintrack.R
//import kotlinx.android.synthetic.main.activity_new_budget.*

class NewBudget : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_budget)

//        btnsetNB.setOnClickListener {
//            val income = etincomeNB.text.toString().toDoubleOrNull()
//            if (income != null) {
//                generatePlans(income)
//            } else {
//                etincomeNB.error = "Please enter a valid income amount"
//            }
//        }
//    }
//
//    private fun generatePlans(income: Double) {
//        // Clear existing plans/cards
//        llPlans.removeAllViews()
//
//        for (i in 1..3) {
//            val planView = LayoutInflater.from(this).inflate(R.layout.plan_card, null)
//            val cardView = planView.findViewById<CardView>(R.id.cardView)
//            val savingsTextView = planView.findViewById<TextView>(R.id.tvSavings)
//            val expensesTextView = planView.findViewById<TextView>(R.id.tvExpenses)
//
//            // Calculate savings and expenses for the plan
//            val savings = income * 0.2 * i
//            val expenses = income * 0.8 * i
//
//            savingsTextView.text = "Savings: Rs $savings"
//            expensesTextView.text = "Expenses: Rs $expenses"
//
//            llPlans.addView(planView)
//        }
    }
}