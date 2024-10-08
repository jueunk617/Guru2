package com.example.guru2.Auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.guru2.Board.HomeActivity
import com.example.guru2.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.text.InputType

class LoginDialog: AppCompatActivity() {
    lateinit var Auth: FirebaseAuth
    lateinit var lp_logindialog_et_id: EditText
    lateinit var lp_logindialog_et_pw: EditText
    lateinit var lp_logindialog_btn: Button
    lateinit var lp_logindialog_iv: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_dialog)

        Auth = Firebase.auth

        lp_logindialog_et_id = findViewById(R.id.lp_logindialog_et_id)
        lp_logindialog_et_pw = findViewById(R.id.lp_logindialog_et_pw)
        lp_logindialog_btn = findViewById(R.id.lp_logindialog_btn)
        lp_logindialog_iv = findViewById(R.id.lp_logindialog_iv)

        lp_logindialog_et_pw.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        // 이메일 주소 형식으로 보이도록 inputType 지정
        lp_logindialog_et_id.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

        // 아이디 창에 @swu.ac.kr 미리 설정
        lp_logindialog_et_id.setText("@swu.ac.kr")

        lp_logindialog_btn.setOnClickListener {
            val EmailId = lp_logindialog_et_id.text.toString().trim()
            val password = lp_logindialog_et_pw.text.toString().trim()

            // 이메일 주소가 @swu.ac.kr로 끝나는 경우에만 로그인 시도
            if (!EmailId.endsWith("@swu.ac.kr")) {
                Toast.makeText(this, "서울여자대학교 이메일(@swu.ac.kr)을 사용해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Auth.signInWithEmailAndPassword(EmailId, password)
                .addOnCompleteListener(this@LoginDialog) { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        // 로그인 성공
                        Toast.makeText(this@LoginDialog, "로그인되었습니다.", Toast.LENGTH_SHORT).show()
                        // 다음 페이지로 이동
                        val intent = Intent(this@LoginDialog, HomeActivity::class.java)
                        startActivity(intent)
                        finish() // 현재 로그인 액티비티를 종료-> 뒤로 가기 시 로그인 페이지로 돌아가지 않음
                    } else {
                        // 로그인 실패
                        Toast.makeText(this@LoginDialog, "다시 입력해주세요.", Toast.LENGTH_SHORT).show()
                        Log.d("LoginDialog", "signInWithEmail:failure", task.exception)
                    }
                }
        }

        lp_logindialog_iv.setOnClickListener {
            // x 버튼을 누르면 MainActivity로 이동
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}