package com.example.fintrack.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.app.NotificationCompat
import com.example.fintrack.R
import kotlinx.android.synthetic.main.activity_select_budget.*

class SelectBudget : AppCompatActivity() {

    var startPoint = 0
    var endPoint = 0
    var num = 0


    companion object {
        private const val CHANNEL_ID = "BudgetNotificationChannel"
    }

    //private val CHANNEL_ID = "BudgetNotification"
    private val NOTIFICATION_ID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_budget)

        val ivbackwaredSB = findViewById<ImageView>(R.id.ivbackwaredSB)


        ivbackwaredSB.setOnClickListener {
            val intent = Intent(this, NewBudget::class.java)
            startActivity(intent)
        }
        
        val imgHoneiconSB = findViewById<ImageView>(R.id.imgHoneiconSB)


        imgHoneiconSB.setOnClickListener{
            val intent=Intent(this, Home::class.java)
            startActivity(intent)
        }

        val imgWalleticonSB = findViewById<ImageView>(R.id.imgWalleticonSB)


        imgWalleticonSB.setOnClickListener{
            val intent=Intent(this, WalletActivity::class.java)
            startActivity(intent)
        }

        val imgUsericonSB = findViewById<ImageView>(R.id.imgUsericonSB)


        imgUsericonSB.setOnClickListener{
            val intent=Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

        val days = ArrayList<String>()
        for (i in 1..30) {
            days.add(i.toString())
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, days)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spDaysSB.adapter = adapter

        spDaysSB.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedDay = parent.getItemAtPosition(position).toString()
                // Do something with the selected day
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }



        sbExpenseSB.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                lbExpenseValueSB.text = "Rs " + progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {


            }


        })

        sbSavingsSB.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                lbSavingsValueSB.text = "Rs " + progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {


            }


        })



        btnincrementSB.setOnClickListener {
            num++

            lbUsersSB.text = num.toString()
        }

        btndecrementSB.setOnClickListener {
            num--

            lbUsersSB.text = num.toString()
        }

        btnconfirmSB.setOnClickListener {
            val intent = Intent(this, NewBudget::class.java)
            intent.putExtra("expense", sbExpenseSB.progress)
            intent.putExtra("savings", sbSavingsSB.progress)
            intent.putExtra("days", spDaysSB.selectedItem.toString().toInt())
            intent.putExtra("users", num)
            startActivity(intent)
//            showToast("Budget successfully created")
            showNotification("Budget successfully created")

        }


    }

    private fun showNotification(message: String) {
        createNotificationChannel()

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("Budget Notification")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Budget Channel"
            val channelDescription = "Channel for budget notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, channelName, importance)
            channel.description = channelDescription

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

//    private fun showToast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }
    }


// SelectBudget Activity
//package com.example.fintrack.activities
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import android.widget.ImageView
//import android.widget.SeekBar
//import com.example.fintrack.R
//import com.example.fintrack.activities.NewBudget
//import kotlinx.android.synthetic.main.activity_select_budget.*
//
//class SelectBudget : AppCompatActivity() {
//
//    private var expenseValue = 0
//    private var savingValue = 0
//    private var days = 0
//    private var users = 0
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_select_budget)
//
//        val ivbackwaredSB = findViewById<ImageView>(R.id.ivbackwaredSB)
//
//        ivbackwaredSB.setOnClickListener{
//            val intent= Intent(this, NewBudget::class.java)
//            startActivity(intent)
//        }
//
//        val days = ArrayList<String>()
//        for (i in 1..30) {
//            days.add(i.toString())
//        }
//
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, days)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spDaysSB.adapter = adapter
//
//        spDaysSB.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
//                days = parent.getItemAtPosition(position).toString().toInt()
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>) {
//                // Do nothing
//            }
//        }
//
//        sbExpenseSB.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                expenseValue = progress
//                lbExpenseValueSB.text = "Rs $expenseValue"
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                // Do nothing
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                // Do nothing
//            }
//        })
//
//        sbSavingsSB.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                savingValue = progress
//                lbSavingsValueSB.text = "Rs $savingValue"
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                // Do nothing
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                // Do nothing
//            }
//        })
//
//        btnincrementSB.setOnClickListener {
//            users++
//            lbUsersSB.text = users.toString()
//        }
//
//        btndecrementSB.setOnClickListener {
//            if (users > 0) {
//                users--
//                lbUsersSB.text = users.toString()
//            }
//        }
//
//        btnconfirmSB.setOnClickListener {
//            val intent = Intent(this, NewBudget::class.java).apply {
//                putExtra("expenseValue", expenseValue)
//                putExtra("savingValue", savingValue)
//                putExtra("days", days)
//                putExtra("users", users)
//            }
//            startActivity(intent)
//        }
//    }
//}








