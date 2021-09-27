package com.example.taskmanager.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDAO {

    //Insert the data into database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(taskModel: TaskModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTasks(taskModel: ArrayList<TaskModel>)

    //Inside we need to pass the query.
    @Query("select * from task_manager")
    fun getTaskData(): LiveData<List<TaskModel>>

    //Update the data into database
    @Update
    fun updateTask(taskModel: TaskModel)

    //Delete the record from Database
    @Delete
    fun deleteTask(taskModel: TaskModel)
}