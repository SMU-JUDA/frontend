package com.example.juda

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {
    val TAG: String = "MainActivity"
    val base_url = "http://ec2-18-117-147-62.us-east-2.compute.amazonaws.com:8080/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl(base_url) // 주소는 본인의 서버 주소로 설정
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(SignService::class.java)

        // 로그인 버튼
        button_login.setOnClickListener {
            var id = editText.text.toString()
            var pw = editText2.text.toString()

            service.login(id, pw).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val result = response.body()
                    Log.d("로그인 성공", "${result}")
                    dialog("success")
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("로그인 실패", "${t.localizedMessage}")
                    dialog("fail")
                }
            })

//            val sharedPreference = getSharedPreferences("register file", Context.MODE_PRIVATE)
//            val savedId = sharedPreference.getString("id", "")
//            val savedPw = sharedPreference.getString("pw", "")
//
//            if (id == savedId && pw == savedPw) {
//                dialog("success")
//            }
//            else
//                dialog("fail")
        }

        // 회원가입 버튼
        button_register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return true
    }
    fun dialog(type: String){
        val dialog = AlertDialog.Builder(this)

        if(type.equals("success")){
            dialog.setTitle("로그인 성공")
            dialog.setMessage("로그인 성공!")
            val intent = Intent(this, MainPageActivity::class.java)
            startActivity(intent)
        }
        else if(type.equals("fail")){
            dialog.setTitle("로그인 실패")
            dialog.setMessage("아이디와 비밀번호를 확인해주세요")
        }

        val dialog_listener = object: DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which){
                    DialogInterface.BUTTON_POSITIVE ->
                        Log.d(TAG, "")
                }
            }
        }

        dialog.setPositiveButton("확인", dialog_listener)
        dialog.show()
    }
}