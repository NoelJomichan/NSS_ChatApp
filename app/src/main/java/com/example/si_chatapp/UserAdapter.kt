package com.example.si_chatapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.si_chatapp.ChatFragment
import com.google.firebase.auth.FirebaseAuth

class UserAdapter(val context: Fragment, val userList: ArrayList<User>):

//class UserAdapter(val userList: ArrayList<User>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {


        val currentUser = userList[position]

        holder.textName.text = currentUser.name


        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {

                val bundle = Bundle()
                bundle.putString("name", currentUser.name)
//                bundle.putString("number", currentUser.number)
//                bundle.putString("email", currentUser.email)
//                bundle.putString("uid", currentUser.uid)
//                bundle.putBoolean("choice", currentUser.choice)

                val activity = v!!.context as AppCompatActivity

                val userProfileFragment = UserProfileFragment()
                userProfileFragment.arguments = bundle
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, userProfileFragment).addToBackStack(null).commit()

            }

        })

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textName = itemView.findViewById<TextView>(R.id.user_name)
//        val rootView = itemView.rootView
    }


//    private var onItemClickListener:((User)->Unit)? = null
//    fun setOnItemClickListener(listener: (User)->Unit) {
//        onItemClickListener = listener
//    }


}