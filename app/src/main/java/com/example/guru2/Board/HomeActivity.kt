package com.example.guru2.Board

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.guru2.R
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var hpButton: Button
    private lateinit var hp_btndeli: Button
    private lateinit var hp_btnorder: Button

    //private lateinit var cardView: ViewGroup
    //private lateinit var hp_textview4: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        hpButton = findViewById(R.id.hp_btn)
        hp_btndeli = findViewById(R.id.hp_btndeli)
        hp_btnorder = findViewById(R.id.hp_btnorder)

        //cardView = findViewById(R.id.cardView)
        //hp_textview4 = findViewById(R.id.hp_textview4)

        hpButton.setOnClickListener {
            Toast.makeText(this@HomeActivity, "참여가 완료되었습니다.", Toast.LENGTH_SHORT).show()
        }

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

    //fun expand(view: View) {
        //val visibility = if (hp_textview4.visibility == View.GONE) View.VISIBLE else View.GONE
        //TransitionManager.beginDelayedTransition(cardView, AutoTransition())
        //hp_textview4.visibility = visibility
    //}
}