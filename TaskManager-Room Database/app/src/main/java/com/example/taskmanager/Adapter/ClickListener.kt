package com.example.taskmanager.Adapter

import com.example.taskmanager.Database.TaskModel

interface ClickListener {

    fun onUpdateClick(taskModel: TaskModel)
    fun onDeleteClick(taskModel: TaskModel)
}