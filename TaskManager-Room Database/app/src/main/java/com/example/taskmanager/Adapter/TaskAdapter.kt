package com.example.taskmanager.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.Database.TaskModel
import com.example.taskmanager.R

class TaskAdapter(
    val context: Context,
    val taskList: MutableList<TaskModel>,
    val clickListener: ClickListener
) : RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.task_item_layout, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.taskName.text = task.title
        holder.tvDate.text = task.date
        holder.tvTime.text = task.date
        holder.tvDecs.text = task.decs

        holder.tvDelete.setOnClickListener {
            clickListener.onDeleteClick(task)
        }
        holder.tvUpdate.setOnClickListener {
            clickListener.onUpdateClick(task)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}