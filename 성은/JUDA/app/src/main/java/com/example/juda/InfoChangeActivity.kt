package com.example.juda

import android.content.Intent
import android.os.Bundle
import android.view.ActionMode
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_inforchange.*

class InfoChangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inforchange)
        change_info_finish_bfn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "수정이 완료 되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}
