package com.example.si_chatapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.button.MaterialButtonToggleGroup

@SuppressLint("StaticFieldLeak")
private lateinit var next_btn: Button

class Switch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch)


        next_btn = findViewById(R.id.next_btn)

        next_btn.setOnClickListener{
            val intent = Intent(this@Switch, MainActivity::class.java)
            startActivity(intent)
        }

    }



}