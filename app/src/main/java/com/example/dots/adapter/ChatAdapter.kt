package com.example.dots.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dots.dataClass.ChatItem
import com.example.dots.R


class ChatAdapter(private val chatList: List<ChatItem>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProfile: ImageView = itemView.findViewById(R.id.imgProfile)
        val txtName: TextView = itemView.findViewById(R.id.storeName)
        val txtMessage: TextView = itemView.findViewById(R.id.storeMessage)
        val txtTime: TextView = itemView.findViewById(R.id.txtTime)
        val imgStatus: ImageView = itemView.findViewById(R.id.imgStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chatList[position]
        holder.imgProfile.setImageResource(chat.imageResId)
        holder.txtName.text = chat.name
        holder.txtMessage.text = chat.message
        holder.txtTime.text = chat.time
        holder.imgStatus.visibility = if (chat.isRead) View.VISIBLE else View.GONE
    }

    override fun getItemCount() = chatList.size
}
