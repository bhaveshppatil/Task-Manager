package com.example.taskmanager.Adapter

import android.view.View
import com.example.taskmanager.R
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
    var taskName:TextView = itemView.findViewById(R.id.tvTaskName);
    var tvDate:TextView = itemView.findViewById(R.id.tvDate);
    var tvTime:TextView = itemView.findViewById(R.id.tvTime);
    var tvDecs:TextView = itemView.findViewById(R.id.tvDecs);
    var tvUpdate:TextView = itemView.findViewById(R.id.tvEdit);
    var tvDelete:TextView = itemView.findViewById(R.id.tvDelete);

}