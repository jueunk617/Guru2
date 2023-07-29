package com.example.guru2.Auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.guru2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinDialog : AppCompatActivity() {
    private lateinit var Auth: FirebaseAuth
    private lateinit var lp_memberdialog_edtemail: EditText
    private lateinit var lp_memberdialog_edtpw: EditText
    private lateinit var lp_memberdialog_edtname: EditText
    private lateinit var lp_member_btn: Button
    private lateinit var lp_memberdialog_closebtn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.member_dialog)

        Auth = Firebase.auth
        lp_memberdialog_edtemail = findViewById(R.id.lp_memberdialog_edtemail)
        lp_memberdialog_edtpw = findViewById(R.id.lp_memberdialog_edtpw)
        lp_memberdialog_edtname = findViewById(R.id.lp_memberdialog_edtname)
        lp_member_btn = findViewById(R.id.lp_member_btn)
        lp_memberdialog_closebtn = findViewById(R.id.lp_memberdialog_closebtn)

        lp_member_btn.setOnClickListener {
            val email = lp_memberdialog_edtemail.text.toString().trim()
            val password = lp_memberdialog_edtpw.text.toString().trim()
            val nickname = lp_memberdialog_edtname.text.toString().trim()

            // 이메일 형식을 확인하기 위한 정규 표현식
            val emailPattern = Regex("[a-zA-Z0-9._%+-]+@swu\\.ac\\.kr")

            // 이메일 형식 검사
            if (!email.matches(emailPattern)) {
                Toast.makeText(this, "서울여자대학교 이메일(@swu.ac.kr)을 사용해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // 회원가입 성공
                        val user: FirebaseUser? = Auth.currentUser
                        Toast.makeText(this, "회원가입이 완료되었습니다. 다시 로그인해주세요.", Toast.LENGTH_SHORT).show()
                        // 회원가입 후 홈화면으로 이동 추가
                    } else {
                        // 회원가입 실패
                        Toast.makeText(this, "회원가입에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        lp_memberdialog_closebtn.setOnClickListener {
            // x 버튼을 누르면 MainActivity로 이동
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // 이 다이얼로그 액티비티를 종료해서 뒤로 가기 버튼을 눌러 돌아가지 않도록 함
        }

    }
}