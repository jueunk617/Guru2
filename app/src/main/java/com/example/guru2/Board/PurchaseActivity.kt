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

class PurchaseActivity : AppCompatActivity() {

    // 임시로 데이터를 생성하여 사용
    private val postList = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.boardpage_purchase)

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
                "공동구매",
                "2023.07.29",
                "휴지 공구하실 분 구합니다 !!",
                "쿠팡에서 10개에 12000원에 파는데 5개만 필요해서 같이 사실 분 구해요~",
                listOf("휴지", "롤휴지", "쿠팡","기숙사"),
                1,
                8
            )
        )

        postList.add(
            Post(
                "공동구매",
                "2023.07.30",
                "생리대 10팩 샀는데 나누실 분 구해요!",
                "혼자 쓰기 너무 많아서 나누실 분 구합니다.",
                listOf("생리대", "좋은느낌", "기숙사"),
                3,
                12
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