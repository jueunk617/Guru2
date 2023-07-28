package com.example.guru2.Board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.guru2.R

class HomeActivity : AppCompatActivity() {

    private lateinit var hp_btndeli: Button
    private lateinit var hp_btnorder: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        hp_btndeli = findViewById(R.id.hp_btndeli)
        hp_btnorder = findViewById(R.id.hp_btnorder)

        // 배달주문 메뉴 클릭
        hp_btndeli.setOnClickListener {
            val intent = Intent(this, DeliveryActivity::class.java)
            startActivity(intent)
        }

        // 공동구매 메뉴 클릭
        hp_btnorder.setOnClickListener {
            val intent = Intent(this, PurchaseActivity::class.java)
            startActivity(intent)
        }
    }
}