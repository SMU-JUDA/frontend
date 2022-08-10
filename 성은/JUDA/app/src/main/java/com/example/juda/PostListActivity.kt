package com.example.juda

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_post_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostListActivity : AppCompatActivity() {
    val base_url = "http://ec2-18-117-147-62.us-east-2.compute.amazonaws.com:8080/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        val retrofit = Retrofit.Builder()
            .baseUrl(base_url) // 주소는 본인의 서버 주소로 설정
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PostService::class.java)

        service.get_post().enqueue(object : Callback<List<PostResponse>> {
            override fun onResponse(call: Call<List<PostResponse>>, response: Response<List<PostResponse>>) {
                val result = response.body()
                Log.d("포스트 성공", "${result}")
            }
            override fun onFailure(call: Call<List<PostResponse>>, t: Throwable) {
                Log.e("포스트 실패", "${t.localizedMessage}")
            }
        })

        print(service.get_post())

        // 글 작성하기 버튼 클릭
        button_write.setOnClickListener {
            val intent = Intent(this, PostActivity::class.java)
            startActivity(intent)
        }
    }

}