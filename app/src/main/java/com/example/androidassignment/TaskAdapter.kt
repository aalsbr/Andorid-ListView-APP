package com.example.androidassignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val context: Context, private var tasklist: ArrayList<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        )
    }
    override fun getItemCount(): Int = tasklist.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task_adapter: Task = tasklist[position]

        when (task_adapter.pirioty.toString()) {

            "1" -> holder.circle_view.setColorFilter(
                ContextCompat.getColor(
                    context,
                    R.color.lowcolor
                )
            );
            "2" -> holder.circle_view.setColorFilter(
                ContextCompat.getColor(
                    context,
                    R.color.mediumhcolo
                )
            );

            "3" -> holder.circle_view.setColorFilter(
                ContextCompat.getColor(
                    context,
                    R.color.highcolor
                )
            );
            else -> holder.circle_view.setColorFilter(
                ContextCompat.getColor(
                    context,
                    R.color.design_default_color_error
                )
            );
        }
        holder.description.text = task_adapter.description
        // holder.priority.text=user.pirioty.toString()


    }

    fun setData(tasklist_data: ArrayList<Task>) {
        this.tasklist = tasklist_data
        notifyDataSetChanged()
    }

    fun getpostion(position: Int): Task {
        return tasklist.get(position)
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var description: TextView = itemView.findViewById(R.id.text_description)

        // var priority:TextView=itemView.findViewById(R.id.text_view_priority)
        var circle_view: ImageView = itemView.findViewById(R.id.circle_view)
    }


}