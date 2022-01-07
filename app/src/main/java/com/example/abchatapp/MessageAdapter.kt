package com.example.abchatapp

import android.content.Context
import android.provider.Telephony
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MessageAdapter(val context: Context,val messageList: ArrayList<Message>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val itemreceived = 1
    val itemsent = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==1){
            val view: View = LayoutInflater.from(context).inflate(R.layout.recieved,parent,false)
            return ReceiveViewHolder(view)
        }else{
            val view: View = LayoutInflater.from(context).inflate(R.layout.sent,parent,false)
            return SentViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder.javaClass == SentViewHolder::class.java){
            val currentmessage = messageList[position]
            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentmessage.message

        }else{
            val currentmessage = messageList[position]
            val veiwHolder = holder as ReceiveViewHolder
            holder.receivedMessage.text = currentmessage.message
        }

    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId))
            return itemsent
        else
            return itemreceived
    }

    override fun getItemCount(): Int {
        return messageList.size

    }



    class SentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val sentMessage = itemView.findViewById<TextView>(R.id.sentmessage)
    }
    class ReceiveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val receivedMessage = itemView.findViewById<TextView>(R.id.receivedmessage)
    }

}