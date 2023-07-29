package com.example.guru2.Board

import android.content.Intent
import android.os.Bundle
//import android.view.View
import android.widget.Button
import com.example.guru2.R
//import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.AppCompatButton
//import androidx.cardview.widget.CardView

class HomeActivity : AppCompatActivity() {

    private lateinit var hp_btndeli: Button
    private lateinit var hp_btnorder: Button

    //private lateinit var baseView: CardView
    //private lateinit var hiddenView: CardView
    //private lateinit var hp_enterButton: AppCompatButton
    //private var isExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        hp_btndeli = findViewById(R.id.hp_btndeli)
        hp_btnorder = findViewById(R.id.hp_btnorder)

        //baseView = findViewById(R.id.baseView)
        //hiddenView = findViewById(R.id.hiddenView)
        //hp_enterButton = findViewById(R.id.hp_enterButton)

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

        //baseView.setOnClickListener {
            //isExpanded = !isExpanded
            //if (isExpanded) {
                //expandHiddenView()
            //} else {
                //collapseHiddenView()
            //}
        //}

        //hp_enterButton.setOnClickListener {
            //showMessage("참여가 완료되었습니다.")
        //}
    }

    //private fun expandHiddenView() {
        //hiddenView.visibility = View.VISIBLE
    //}

    //private fun collapseHiddenView() {
        //hiddenView.visibility = View.GONE
    //}

    //private fun showMessage(message: String) {
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    //}
}