package com.example.fintrack.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import com.example.fintrack.R
//import kotlinx.android.synthetic.main.activity_select_budget.*

class SelectBudget : AppCompatActivity() {

    var startPoint = 0
    var endPoint = 0
    var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_budget)

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
//                val selectedDay = parent.getItemAtPosition(position).toString()
//                // Do something with the selected day
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>) {
//                // Do nothing
//            }
//        }
//
//
//
//        sbExpenseSB.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                lbExpenseValueSB.text = "Rs " + progress.toString()
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//
//
//            }
//
//
//        })
//
//        sbSavingsSB.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                lbSavingsValueSB.text = "Rs " + progress.toString()
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//
//
//            }
//
//
//        })
//
//
//
//        btnincrementSB.setOnClickListener {
//            num++
//
//            lbUsersSB.text = num.toString()
//        }
//
//        btndecrementSB.setOnClickListener {
//            num--
//
//            lbUsersSB.text = num.toString()
//        }

    }
}