package com.example.si_chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var edtEmail : EditText
    private lateinit var edtPassword : EditText
    private lateinit var loginBtn : Button
    private lateinit var signupBtn : Button

    private lateinit var mAuth : FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__login)


        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        loginBtn = findViewById(R.id.Login_btn)
        signupBtn = findViewById(R.id.Signup_btn)


        signupBtn.setOnClickListener {
            val intent = Intent(this@Login, Signup::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            login(email, password)
        }

    }

    private fun login(email: String, password: String){

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent = Intent(this@Login, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@Login, "User Doesn't Exist", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@Login, Signup::class.java)
                    startActivity(intent)
                }
            }

    }


}