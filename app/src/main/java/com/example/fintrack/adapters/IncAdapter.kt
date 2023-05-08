package com.example.fintrack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.models.IncomeModel

class IncAdapter (private val incList: ArrayList<IncomeModel>): RecyclerView.Adapter<IncAdapter.ViewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.inc_list_item,parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentInc = incList[position]
        holder.lbIncName.text = currentInc.incName
        holder.lbIncCat.text = currentInc.incCategory
        holder.lbIncAmt.text = currentInc.incAmount
        holder.lbIncDat.text = currentInc.incDate
    }

    override fun getItemCount(): Int {
        return incList.size
    }



    class ViewHolder(itemView: View, clickListener:onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val lbIncName : TextView = itemView.findViewById(R.id.lbIncName)
        val lbIncCat : TextView = itemView.findViewById(R.id.lbIncCat)
        val lbIncAmt : TextView = itemView.findViewById(R.id.lbIncAmt)
        val lbIncDat : TextView = itemView.findViewById(R.id.lbIncDat)

        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}