package com.example.guru2.Board

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.guru2.R
import com.example.guru2.data.PostType

class AddpostDialog : AppCompatActivity() {
    private lateinit var dBtn: Button
    private lateinit var pBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addpost_dialog)
        Log.i("##INFO", "testmessage : ")

        dBtn = findViewById(R.id.addpost_dialog_btn1)
        pBtn = findViewById(R.id.addpost_dialog_btn2)

        dBtn.setOnClickListener {
            Log.i("##INFO", "dBtn.setOnClickListener")
            movePage(PostType.Delivery)
        }
        pBtn.setOnClickListener {
            movePage(PostType.Purchase)
        }

        val xButton: ImageView = findViewById(R.id.imageView)
        xButton.setOnClickListener {
            finish()
        }
    }

    private fun movePage(type: PostType) {
        val i = Intent(this, WritePostActivity::class.java)
        i.putExtra("type", type)
        startActivity(i)
        finish()
    }
}