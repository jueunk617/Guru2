package com.example.guru2.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.guru2.R

class MainActivity : AppCompatActivity() {

    lateinit var lp_btnweblog: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginpage)

        lp_btnweblog = findViewById(R.id.lp_btnweblog)

        lp_btnweblog.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginDialog::class.java)
            startActivity(intent)
        }
    }
}