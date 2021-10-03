package com.example.androidassignment


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {


    private lateinit var taskViewModel: TaskViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        taskAdapter = TaskAdapter(this, ArrayList<Task>())
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        taskViewModel.getALLData(this).observe(this, Observer {
            taskAdapter.setData(it as ArrayList<Task>)
        })

        //set swip to delete
        val swipeHandler = object : TaskSwaip(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as TaskAdapter
                taskViewModel.delete(this@MainActivity, adapter.getpostion(viewHolder.adapterPosition))

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)


        // FAB  SET ONCLICK
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            openActivityForResult()
        }


    }// OnCreate END


    fun openActivityForResult() {
        startForResult.launch(Intent(this, AddTask::class.java))
    }


    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            var description = data!!.getStringExtra(AddTask.EX_DESCRIPTION)
            var priority: Int = (data!!.getStringExtra(AddTask.EX_PIORITY)).toInt()

            taskViewModel.insert(this, Task(description, priority))
            Toast.makeText(applicationContext, "Data added successfully..", Toast.LENGTH_SHORT).show()
        }

    }


}