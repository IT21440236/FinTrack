package com.example.fintrack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.models.Transaction

class AdapterTransactionClass(private val transList: ArrayList<Transaction>): RecyclerView.Adapter<AdapterTransactionClass.ViewHolderClass>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val transView = LayoutInflater.from(parent.context).inflate(R.layout.translayout, parent, false)
        return ViewHolderClass(transView)
    }

    override fun getItemCount(): Int {
        return transList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = transList[position]
        holder.rvImage.setImageResource(currentItem.transImage)
        holder.rvTitle.text = currentItem.TransTitle
        holder.rvTitle2.text = currentItem.TransTitle2
        holder.rvAmount.text = currentItem.TransAmount
        holder.rvDate.text = currentItem.TransDate
    }

    class ViewHolderClass(itemView: View):RecyclerView.ViewHolder(itemView) {
        val rvImage: ImageView = itemView.findViewById(R.id.TransImage)
        val rvTitle: TextView = itemView.findViewById(R.id.TransTitle)
        val rvTitle2: TextView = itemView.findViewById(R.id.TransTitle2)
        val rvAmount: TextView = itemView.findViewById(R.id.TransAmount)
        val rvDate: TextView = itemView.findViewById(R.id.TransDate)
    }
}