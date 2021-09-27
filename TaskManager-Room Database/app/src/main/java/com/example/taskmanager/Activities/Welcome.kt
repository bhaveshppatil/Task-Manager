package com.example.taskmanager.Activities

import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.Adapter.ClickListener
import com.example.taskmanager.Adapter.TaskAdapter
import com.example.taskmanager.Database.TaskDAO
import com.example.taskmanager.Database.TaskModel
import com.example.taskmanager.Database.TaskRoomDatabase
import com.example.taskmanager.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Welcome : AppCompatActivity(), ClickListener {

    lateinit var taskAdapter: TaskAdapter
    private var taskModelList = mutableListOf<TaskModel>()

    lateinit var roomDatabase: TaskRoomDatabase
    lateinit var taskDAO: TaskDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        roomDatabase = TaskRoomDatabase.getDatabaseObject(this)
        taskDAO = roomDatabase.getTaskDAO()
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {

            crdAddTaskView.visibility = View.VISIBLE

            val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.high -> Toast.makeText(
                        this,
                        "High Priority Task",
                        Toast.LENGTH_SHORT
                    ).show()
                    R.id.low -> Toast.makeText(
                        this,
                        "Low Priority Task",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            btnAddTask.setOnClickListener {
                val title: String = etTaskName.text.toString()
                val date: String = etDate.text.toString()
                val timer: String = etTime.text.toString()
                val decs: String = etDecs.text.toString()

                val addNewTask = TaskModel(title, decs, date, timer)
                CoroutineScope(Dispatchers.IO).launch {
                    taskDAO.addTask(addNewTask)
                }
                crdAddTaskView.visibility = View.GONE
            }
        }
        taskAdapter = TaskAdapter(this, taskModelList, this)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = taskAdapter

        taskDAO.getTaskData().observe(this, Observer {
            val taskModel = it;
            taskModelList.clear()
            taskModelList.addAll(taskModel)
            taskAdapter.notifyDataSetChanged()
        })
    }

    override fun onUpdateClick(taskModel: TaskModel) {
        crdAddTaskView.visibility = View.VISIBLE

        btnAddTask.setOnClickListener {

            val newTitle: String = etTaskName.text.toString()
            val newDate: String = etDate.text.toString()
            val newTimer: String = etTime.text.toString()
            val newDecs: String = etDecs.text.toString()

            taskModel.title = newTitle
            taskModel.decs = newDecs
            taskModel.date = newDate
            taskModel.time = newTimer

            CoroutineScope(Dispatchers.IO).launch {
                taskDAO.updateTask(taskModel)
            }
            crdAddTaskView.visibility = View.GONE
        }
    }

    override fun onDeleteClick(taskModel: TaskModel) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDAO.deleteTask(taskModel)
        }
    }

}