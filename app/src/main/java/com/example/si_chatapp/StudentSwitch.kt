package com.example.si_chatapp

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class StudentSwitch : AppCompatActivity() {

    private lateinit var studentDob : TextView
    private lateinit var calender : Button

    private lateinit var nextbtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_switch)

        studentDob = findViewById(R.id.student_dob)
        calender = findViewById(R.id.calendar)

        nextbtn = findViewById(R.id.next_btn)

        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            updateLabel(myCalendar)
        }

        calender.setOnClickListener{
            DatePickerDialog(this, datePicker, myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        nextbtn.setOnClickListener {
            Intent(this@StudentSwitch, MainActivity::class.java).also {
                startActivity(it)
            }
        }


    }

    private fun updateLabel(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        studentDob.setText(sdf.format(myCalendar.time))
    }
}