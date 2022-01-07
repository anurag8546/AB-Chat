package com.example.abchatapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class UserAdapter(val context: Context, val userList: ArrayList<User>)
    : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.userlayout,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val CurrentUser = userList[position]

        holder.textname.text=CurrentUser.name

        holder.itemView.setOnClickListener{
            val intent=Intent(context,Chat::class.java)

            intent.putExtra("name",CurrentUser.name)
            intent.putExtra("uid",CurrentUser.uid)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textname = itemView.findViewById<TextView>(R.id.txt_name)

    }
}