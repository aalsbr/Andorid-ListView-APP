package com.example.androidassignment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddTask : AppCompatActivity() {
    companion object {
        const val EX_DESCRIPTION = "com.example.androidassignment.Ex_DESCRIPTION"
        const val EX_PIORITY = "com.example.androidassignment.EX_PIORITY"
    }

    private lateinit var description_text: EditText
    private lateinit var pirioty_group: RadioGroup
    private lateinit var add_btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        setTitle("ADD TASK")

        //Assining
        description_text = findViewById(R.id.edit_description)
        pirioty_group = findViewById(R.id.radioGroup);
        add_btn = findViewById(R.id.add_button)


        //When user Click Add Btn
        add_btn.setOnClickListener {
            addtask()
        }
    }// OnCreate END


    //add new task
    fun addtask() {

        val selectedId: Int = pirioty_group.getCheckedRadioButtonId()
        var descriptionText = description_text.text.toString()


        //Validiting edittext
        if (descriptionText.trim().isEmpty()) {
            Toast.makeText(applicationContext, "Please fill Description  !", Toast.LENGTH_SHORT)
                .show()
            return
        } else if (selectedId == -1) {
            Toast.makeText(applicationContext, "Please Choos Priority !", Toast.LENGTH_SHORT).show()
            return
        }

        //get the radio button text and make it numbers
        val radio: RadioButton = findViewById(selectedId)
        var priority_set: String

        when (radio.text) {
            "High" -> priority_set = "3"
            "Medium" -> priority_set = "2"
            "Low" -> priority_set = "1"
            else -> return
        }

        //sending data to MainActivity
        val data = Intent(this@AddTask, MainActivity::class.java)
        data.putExtra(AddTask.EX_DESCRIPTION, descriptionText)
        data.putExtra(AddTask.EX_PIORITY, priority_set)
        setResult(RESULT_OK, data)
        finish()

    } //add task end


}


