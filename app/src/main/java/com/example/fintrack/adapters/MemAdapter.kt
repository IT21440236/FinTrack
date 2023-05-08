package com.example.fintrack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.models.MemberModel
import java.text.FieldPosition

class MemAdapter(private val memList: ArrayList<MemberModel>) :
    RecyclerView.Adapter<MemAdapter.ViewHolder>() {

    private lateinit var mListner: onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(clickListner: onItemClickListner){
        mListner = clickListner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.mem_list_item, parent, false)
        return ViewHolder(itemView, mListner)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val currentMem = memList[position]
        holder.tvMemName.text = currentMem.MemberName
    }

    override fun getItemCount(): Int {
        return memList.size
    }

    class ViewHolder (itemView: View, clickListner: onItemClickListner) : RecyclerView.ViewHolder(itemView) {

        val tvMemName: TextView = itemView.findViewById(R.id.tvMemName)

        init {
            itemView.setOnClickListener {
                clickListner.onItemClick(adapterPosition)
            }
        }
    }
}