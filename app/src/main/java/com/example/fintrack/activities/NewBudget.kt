package com.example.fintrack.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import com.example.fintrack.R
import com.example.fintrack.models.BudgetModel
import kotlinx.android.synthetic.main.activity_new_budget.*

class NewBudget : AppCompatActivity() {
    /////mod
//    private var expenseValue: String? = null
//    private var savingValue: String? = null
//    private var days: String? = null
   // private var budget: BudgetModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_budget)

        val btncreatebudgetNB = findViewById<AppCompatButton>(R.id.btncreatebudgetNB)


        btncreatebudgetNB.setOnClickListener{
            val intent= Intent(this, SelectBudget::class.java)
            startActivity(intent)
        }
        
        val imgHomeiconNB = findViewById<ImageView>(R.id.imgHomeiconNB)


        imgHomeiconNB.setOnClickListener{
            val intent=Intent(this, Home::class.java)
            startActivity(intent)
        }

        val imgWalleticonNB = findViewById<ImageView>(R.id.imgWalleticonNB)


        imgWalleticonNB.setOnClickListener{
            val intent=Intent(this, WalletActivity::class.java)
            startActivity(intent)
        }

        val imgUsericonNB = findViewById<ImageView>(R.id.imgUsericonNB)


        imgUsericonNB.setOnClickListener{
            val intent=Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

        /////mod
//        expenseValue = intent.getStringExtra("expenseValue")
//        savingValue = intent.getStringExtra("savingValue")
//        days = intent.getStringExtra("days")

        /////mod
//        @Suppress("DEPRECATION")
//        budget = intent?.extras?.getParcelable("budget")


        btnsetNB.setOnClickListener {
            val income = etincomeNB.text.toString().toDoubleOrNull()
            if (income != null) {
                generatePlans(income)
            } else {
                etincomeNB.error = "Please enter a valid income amount"
            }
        }

        val expense = intent.getIntExtra("expense", 0)
        val savings = intent.getIntExtra("savings", 0)
        val days = intent.getIntExtra("days", 0)
        val users = intent.getIntExtra("users", 0)

        val cardView = findViewById<View>(R.id.cardView)
        val cardExpense = cardView.findViewById<TextView>(R.id.tvExpense)
        val cardSavings = cardView.findViewById<TextView>(R.id.tvSavings)
        val cardDays = cardView.findViewById<TextView>(R.id.tvDays)
        val cardUsers = cardView.findViewById<TextView>(R.id.tvUsers)

        cardExpense.text = "Expense: Rs $expense"
        cardSavings.text = "Savings: Rs $savings"
        cardDays.text = "Days: $days"
        cardUsers.text = "Users: $users"




    }

    private fun generatePlans(income: Double) {
        // Clear existing plans/cards
        llPlans.removeAllViews()

        for (i in 1..3) {
            val planView = LayoutInflater.from(this).inflate(R.layout.plan_card, null)
            val cardView = planView.findViewById<CardView>(R.id.cardView)
            val savingsTextView = planView.findViewById<TextView>(R.id.tvSavings)
            val expensesTextView = planView.findViewById<TextView>(R.id.tvExpenses)

            // Calculate savings and expenses for the plan
            val savings = income * 0.2 * i
            val expenses = income * 0.8 * i

            savingsTextView.text = "Savings: Rs $savings"
            expensesTextView.text = "Expenses: Rs $expenses"

            llPlans.addView(planView)
        }

        /////mod

//        lbcardexpval1NB.text = "Expense Value: $expenseValue"
//        lbcardsavval1NB.text = "Saving Value: $savingValue"
//        lbcarddayNB.text = "Days: $days"
//        budget?.let {
//            lbcardexpval1NB.text = "Expense Value: ${it.expenseValue}"
//            lbcardsavval1NB.text = "Saving Value: ${it.savingValue}"
//            lbcarddayNB.text = "Days: ${it.days}"
//        }
    }
}
