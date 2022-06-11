package com.example.si_chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.Instant

class Signup : AppCompatActivity() {

    private lateinit var edtEmail : EditText
    private lateinit var edtPassword : EditText
    private lateinit var edtName : EditText
    private lateinit var signupBtn : Button

    private lateinit var mAuth : FirebaseAuth
    private lateinit var mDbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
2002

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        edtName = findViewById(R.id.edt_name)
        signupBtn = findViewById(R.id.Signup_btn)

        mAuth = FirebaseAuth.getInstance()

        signupBtn.setOnClickListener {
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            signUp(name, email, password)
        }

    }

    private fun signUp(name: String, email: String, password: String){

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!)

                    val intent = Intent(this@Signup, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@Signup, "Authentication Error(Sign Up)", Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun addUserToDatabase(name: String, email: String, uid: String){

        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("User").child(uid).setValue(User(name, email, uid))

    }
}