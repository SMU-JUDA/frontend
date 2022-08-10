package com.example.juda

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        // 회원정보 수정 페이지로 이동
        change_info_btn.setOnClickListener {
            val intent = Intent(this, InfoChangeActivity::class.java)
            startActivity(intent)
        }

        change_PW_btn.setOnClickListener {
            val intent = Intent(this, PwChangeActivity::class.java)
            startActivity(intent)
        }

    }
}