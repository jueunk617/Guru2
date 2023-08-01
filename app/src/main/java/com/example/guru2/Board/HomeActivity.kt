package com.example.guru2.Board

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import com.example.guru2.R
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout

class HomeActivity : AppCompatActivity() {

    // 배달주문 메뉴 버튼과 공동구매 메뉴 버튼
    private lateinit var hp_btndeli: Button
    private lateinit var hp_btnorder: Button

    // 기본 뷰와 숨겨진 뷰, 뷰 확장/축소를 위한 버튼
    private lateinit var baseView: ConstraintLayout
    private lateinit var hiddenView: ConstraintLayout
    private lateinit var hp_enterButton: AppCompatButton
    private var isExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        hp_btndeli = findViewById(R.id.hp_btndeli)
        hp_btnorder = findViewById(R.id.hp_btnorder)
        baseView = findViewById(R.id.baseView)
        hiddenView = findViewById(R.id.hiddenView)
        hp_enterButton = findViewById(R.id.hp_enterButton)

        // 배달주문 메뉴 버튼 클릭 시, DeliveryActivity로 이동
        hp_btndeli.setOnClickListener {
            val intent = Intent(this, DeliveryActivity::class.java)
            startActivity(intent)
        }

        // 공동구매 메뉴 버튼 클릭 시, PurchaseActivity로 이동
        hp_btnorder.setOnClickListener {
            val intent = Intent(this, PurchaseActivity::class.java)
            startActivity(intent)
        }

        // 기본 뷰(baseView) 클릭 시, 숨겨진 뷰(hiddenView)를 확장, 축소
        baseView.setOnClickListener {
            isExpanded = !isExpanded
            if (isExpanded) {
                expandHiddenView()
            } else {
                collapseHiddenView()
            }
        }

        // 확장된 뷰(hiddenView) 내부의 버튼(hp_enterButton) 클릭 시, 메시지를 띄움
        hp_enterButton.setOnClickListener {
            showMessage("참여가 완료되었습니다.")
        }
    }

    // 숨겨진 뷰(hiddenView)를 확장하는 메서드
    @SuppressLint("ResourceType")
    private fun expandHiddenView() {
        hiddenView.visibility = View.VISIBLE
        val animation = AnimationUtils.loadAnimation(this, R.xml.expand_animation)
        hiddenView.startAnimation(animation)
    }

    // 숨겨진 뷰(hiddenView)를 축소하는 메서드
    private fun collapseHiddenView() {
        hiddenView.visibility = View.GONE
    }

    // 메시지를 토스트로 띄우는 메서드
    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
