package com.example.si_chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.si_chatapp.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Login : AppCompatActivity() {

    private lateinit var edtEmail : EditText
    private lateinit var edtPassword : EditText
    private lateinit var loginBtn : Button
    private lateinit var signupBtn : Button
    private lateinit var emailContainer: TextInputLayout

    private lateinit var mAuth : FirebaseAuth
    private lateinit var mDbRef : DatabaseReference


//    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity__login)

        emailFocusListener()

        mAuth = FirebaseAuth.getInstance()


        mDbRef = FirebaseDatabase.getInstance().reference

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

    private fun emailFocusListener() {

        edtEmail = findViewById(R.id.edt_email)
        emailContainer = findViewById(R.id.email_container)

        edtEmail.setOnFocusChangeListener { _, focused ->
            if(!focused){

                emailContainer.helperText = validEmail()

            }
        }
    }

    private fun validEmail(): String? {

        edtEmail = findViewById(R.id.edt_email)
        val emailText = edtEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            return "Invalid Email Address"
        }

        return null
    }


    private fun login(email: String, password: String){

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    mDbRef.child("User").addValueEventListener(object : ValueEventListener{

                        override fun onDataChange(snapshot: DataSnapshot) {

                            for (postSnapshot in snapshot.children){

                                val currentUser = postSnapshot.getValue(User::class.java)

                                if (currentUser?.choice == true){
                                    val intent = Intent(this@Login, MentorMainActivity::class.java)
                                    startActivity(intent)

                                }else{
                                    val intent = Intent(this@Login, MainActivity::class.java)
                                    startActivity(intent)
                                }

                            }

                        }

                        override fun onCancelled(error: DatabaseError) {
                        }

                    })



//                    val intent = Intent(this@Login, MainActivity::class.java)
//                    startActivity(intent)

                } else {
                    Toast.makeText(this@Login, "User Doesn't Exist", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@Login, Signup::class.java)
                    startActivity(intent)
                }
            }

    }


}