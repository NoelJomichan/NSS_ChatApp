package com.example.si_chatapp

import android.content.Intent
import android.icu.lang.UProperty
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.button.MaterialButtonToggleGroup
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

    private lateinit var toggle_btn_grp: MaterialButtonToggleGroup

    var choice: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)



        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        edtName = findViewById(R.id.edt_name)
        signupBtn = findViewById(R.id.Signup_btn)

        mAuth = FirebaseAuth.getInstance()

        toggle_btn_grp = findViewById(R.id.toggle_btn_grp)

        toggle_btn_grp.addOnButtonCheckedListener{  toggle_btn_grp, checkedId, isChecked ->

            if(isChecked){
                when(checkedId){
                    R.id.guide_btn -> showToastGuide("Guide Button")
                    R.id.student_btn -> showToast("Student Button")
                }
                signupBtn.isEnabled=true

            }else{
                if (toggle_btn_grp.checkedButtonId == View.NO_ID){
                    showToast("Nothing Selected")
                }
                signupBtn.isEnabled=false
            }

        }

        signupBtn.setOnClickListener {
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            signUp(name, email, password)
        }

    }

    private fun showToastGuide(str: String){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
        choice = true
    }

    private fun showToast(str: String){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    private fun signUp(name: String, email: String, password: String){

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    if (choice){
                        addMentorToDatabase(name, email, mAuth.currentUser?.uid!!, choice)
                        Intent(this@Signup, Switch::class.java).also {
                            startActivity(it)
                        }
                    }else{
                        addStudentToDatabase(name, email, mAuth.currentUser?.uid!!, choice)
                        Intent(this@Signup, StudentSwitch::class.java).also {
                            startActivity(it)
                        }
                    }

                } else {
                    Toast.makeText(this@Signup, "Authentication Error(Sign Up)", Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun addMentorToDatabase(name: String, email: String, uid: String, choice: Boolean){

        mDbRef = FirebaseDatabase.getInstance().reference
        mDbRef.child("User").child("Mentor").child(name).setValue(User(name, email, uid, choice))

    }

    private fun addStudentToDatabase(name: String, email: String, uid: String, choice: Boolean){

        mDbRef = FirebaseDatabase.getInstance().reference
        mDbRef.child("User").child("Student").child(name).setValue(User(name, email, uid, choice))

    }
}