package com.example.guru2.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.guru2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var lp_btnweblog: Button
    lateinit var lp_tv1: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginpage)

        auth = Firebase.auth

        lp_btnweblog = findViewById(R.id.lp_btnweblog)
        lp_tv1 = findViewById(R.id.lp_tv1)

        lp_btnweblog.setOnClickListener {
                val intent = Intent(this@MainActivity, LoginDialog::class.java)
                startActivity(intent)
        }

        lp_tv1.setOnClickListener {
            // lp_tv1 텍스트 뷰 클릭 시, JoinDialog로 이동
            val intent = Intent(this@MainActivity, JoinDialog::class.java)
            startActivity(intent)
        }
    }
}