package com.example.dots.activityLoginTrue

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dots.R
import com.example.dots.adapter.ChatAdapter
import com.example.dots.dataClass.ChatItem

class ChatLoggedInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat_logged_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val back = findViewById<ImageView>(R.id.back)
        val chatList = listOf(
            ChatItem("Idaman Concat", "Hati-hati dijalan mas", "09.41", R.drawable.chat_1, true),
            ChatItem("Idaman Gejayan", "Bisa kak, sesuai pesanan ya kak", "03/05/2025", R.drawable.chat_2, false),
            ChatItem("Idaman Maguwo", "Ready kak", "22/04/2025", R.drawable.chat_3, false)
        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ChatAdapter(chatList)


        back.setOnClickListener {
            val intent = Intent(this, HomeLoggedInActivity::class.java)
            startActivity(intent)
        }


    }
}