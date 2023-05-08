package com.example.fintrack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.models.ExpenseModel

class ExpAdapter (private val expList: ArrayList<ExpenseModel>) : RecyclerView.Adapter<ExpAdapter.ViewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.exp_list_item,parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentExp = expList[position]
        holder.lbExpName.text = currentExp.expName
        holder.lbCatName.text = currentExp.expCategory
        holder.lbAmount.text = currentExp.expAmount
        holder.lbDate.text = currentExp.expDate
    }

    override fun getItemCount(): Int {
        return expList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        // Define the ViewHolder members here
        // ...

        val lbExpName : TextView = itemView.findViewById(R.id.lbExpName)
        val lbCatName : TextView = itemView.findViewById(R.id.lbCatName)
        val lbAmount : TextView = itemView.findViewById(R.id.lbAmount)
        val lbDate : TextView = itemView.findViewById(R.id.lbDate)




        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }

    }



}