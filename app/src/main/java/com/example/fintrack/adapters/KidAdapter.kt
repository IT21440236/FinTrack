package com.example.fintrack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.models.KidModel

class KidAdapter(private val kidList: ArrayList<KidModel>) :
    RecyclerView.Adapter<KidAdapter.ViewHolder>(){

    private lateinit var kListner: onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(clickListner: onItemClickListner){
        kListner = clickListner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.kid_list_item, parent, false)
        return ViewHolder(itemView, kListner)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val currentKid = kidList[position]
        holder.tvKidName.text = currentKid.KidName
    }

    override fun getItemCount(): Int {
        return kidList.size
    }

    class ViewHolder (itemView: View, clickListner:onItemClickListner) : RecyclerView.ViewHolder(itemView) {

        val tvKidName: TextView = itemView.findViewById(R.id.tvKidName)

        init {
            itemView.setOnClickListener {
                clickListner.onItemClick(adapterPosition)
            }
        }

    }
}