package com.example.guru2.Board

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.Post.PostAdapter
import com.example.guru2.Post.Post
import com.example.guru2.R

class DeliveryActivity : AppCompatActivity() {

    // 임시로 데이터를 생성하여 사용
    private val postList = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.boardpage_delivery)

        val boardpage_iv1 = findViewById<ImageView>(R.id.boardpage_iv1)
        val btnaddpost = findViewById<Button>(R.id.btnaddpost)
        val post_list_recyclerview = findViewById<RecyclerView>(R.id.post_list_recyclerview)

        boardpage_iv1.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        btnaddpost.setOnClickListener {
            showAddPostDialog()
        }

        // 데이터 추가 (임시)
        postList.add(
            Post(
                "배달/주문",
                "2023.07.29",
                "샐러디 같이 시키실 분 계신가요?",
                "룸메랑 같이 시키려고 합니다. 2명만 모이면 배달비 무료입니다!",
                listOf("샐러디", "샐러드", "샬롬하우스"),
                2,
                10
            )
        )

        postList.add(
            Post(
                "배달/주문",
                "2023.07.30",
                "햄버거 같이 드실 분?",
                "누리관 앞에서 받을 예정입니다 ~",
                listOf("햄버거", "맥도날드", "롯데리아", "누리관"),
                5,
                15
            )
        )

        // RecyclerView 설정
        val layoutManager = LinearLayoutManager(this)
        post_list_recyclerview.layoutManager = layoutManager
        val adapter = PostAdapter(postList)
        post_list_recyclerview.adapter = adapter
    }

    private fun showAddPostDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.addpost_dialog)
        dialog.show()
    }
}