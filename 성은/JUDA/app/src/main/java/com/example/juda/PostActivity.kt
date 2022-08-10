package com.example.juda

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostActivity : AppCompatActivity() {
    val base_url = "http://ec2-18-117-147-62.us-east-2.compute.amazonaws.com:8080/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val retrofit = Retrofit.Builder()
            .baseUrl(base_url) // 주소는 본인의 서버 주소로 설정
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PostService::class.java)

        // 등록 완료 버튼
        button_post_register.setOnClickListener {
            var title = editPostName.text.toString()
            var image = null
            var content = editPost.text.toString()

            service.create_post(title, image, content).enqueue(object : Callback<PostResponse> {
                override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                    val result = response.body()
                    Log.d("포스트 등록 성공", "${result}")
                }
                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    Log.e("포스트 등록 실패", "${t.localizedMessage}")
                }
            })

            val intent = Intent(this, PostListActivity::class.java)
            startActivity(intent)
        }

    }
}