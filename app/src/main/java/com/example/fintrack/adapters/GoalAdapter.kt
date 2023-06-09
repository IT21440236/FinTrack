package com.example.fintrack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.models.GoalModel

class GoalAdapter(private var goalList:ArrayList<GoalModel>) : RecyclerView.Adapter<GoalAdapter.ViewHolder>(){
    private lateinit var mListner:onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

//    fun setFilteredList(mList : ArrayList<GoalModel>){
//        this.goalList = mList
//        notifyDataSetChanged()
//    }


    fun setOnClickListner(clickListner: onItemClickListner){
        mListner = clickListner
    }

    fun submitList(list: List<GoalModel>){
        goalList.clear()
        goalList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.goal_list_item, parent, false)
        return ViewHolder(itemView, mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentGoal = goalList[position]
        holder.tvGoalTitle.text = currentGoal.financialGoal
        holder.tvAmount.text = currentGoal.amount
    }

    override fun getItemCount(): Int {
        return goalList.size
    }

    class ViewHolder(itemView: View, clickListner: onItemClickListner) : RecyclerView.ViewHolder(itemView){
        val tvGoalTitle : TextView = itemView.findViewById(R.id.GoalTitle)
        val tvAmount : TextView = itemView.findViewById(R.id.GoalAmount)

        init{
            itemView.setOnClickListener {
                clickListner.onItemClick(adapterPosition)
            }
        }

    }
}