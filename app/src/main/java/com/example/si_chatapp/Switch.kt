package com.example.si_chatapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

@SuppressLint("StaticFieldLeak")
private lateinit var next_btn: Button

class Switch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch)


        next_btn = findViewById(R.id.next_btn)

        next_btn.setOnClickListener{
            val intent = Intent(this@Switch, MentorMainActivity::class.java)
            startActivity(intent)
        }

    }



}