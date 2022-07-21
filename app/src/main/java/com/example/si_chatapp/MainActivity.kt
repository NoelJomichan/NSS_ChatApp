package com.example.si_chatapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.si_chatapp.databinding.ActivityMainBinding
import com.example.si_chatapp.fragments.ChatFragment
import com.example.si_chatapp.fragments.HomeFragment
import com.example.si_chatapp.fragments.ProfileFragment
import com.example.si_chatapp.fragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private lateinit var bottomNavView: BottomNavigationView

    private lateinit var mAuth: FirebaseAuth        //LogOut


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavView = findViewById(R.id.bottomNavView)
        bottomNavView.background = null
        bottomNavView.menu.getItem(2).isEnabled = false

        replaceFragment(ChatFragment())

        binding.bottomNavView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.navHome -> replaceFragment(HomeFragment())
                R.id.navChat -> replaceFragment(ChatFragment())
                R.id.navSettings -> replaceFragment(SettingsFragment())
                R.id.navProfile -> replaceFragment(ProfileFragment())

                else ->{

                }

            }
            true

        }

//        val homeFragment = HomeFragment()
//        val chatFragment = ChatFragment()
//        val profileFragment = ProfileFragment()
//        val settingsFragment = SettingsFragment()
//

        mAuth = FirebaseAuth.getInstance()      //LogOut
//
//        mDbRef = FirebaseDatabase.getInstance().reference
//
//        userList = ArrayList()
//        adapter = UserAdapter(this, userList)
//
//        userRecyclerView = findViewById(R.id.userRecyclerView)
//
//        userRecyclerView.layoutManager = LinearLayoutManager(this)
//        userRecyclerView.adapter = adapter
//
//        mDbRef.child("User").child("Mentor").addValueEventListener(object: ValueEventListener{
//            @SuppressLint("NotifyDataSetChanged")
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                userList.clear()
//                for (postSnapshot in snapshot.children){
//
//                    val currentUser = postSnapshot.getValue(User::class.java)
//                        userList.add(currentUser!!)
//                }
//                adapter.notifyDataSetChanged()
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        })

//        bottomNavView.setOnItemSelectedListener{
//
//            when(it.itemId){
//
//                R.id.navHome -> replaceFragment(HomeFragment())
//                R.id.navProfile -> replaceFragment(ProfileFragment())
//                R.id.navChat -> replaceFragment(ChatFragment())
//                R.id.navSettings -> replaceFragment(SettingsFragment())
//
//            }
//            true
//
//
//        }


    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()

    }

//    private fun replaceFragment(fragment: Fragment)=
//        supportFragmentManager.beginTransaction().apply{
//            replace(R.id.frameLayout, fragment)
//            commit()
//    }


                                               //LogOut Start
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.logout) {
            mAuth.signOut()
            intent = Intent(this@MainActivity, Login::class.java)
            finish()

            startActivity(intent)
            return true
        }
        return true

    }

    override fun onBackPressed() {
        super.onBackPressed().also {
            Toast.makeText(this, "Back Button pressed", Toast.LENGTH_SHORT).show()
        }
    }

//    fun logout(view: View) {
//            mAuth.signOut()
//            intent = Intent(this@MainActivity, Login::class.java)
//
//            startActivity(intent)
//
//    }
    //LogOut End

}