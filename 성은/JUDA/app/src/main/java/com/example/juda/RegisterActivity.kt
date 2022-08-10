package com.example.juda

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class RegisterActivity : AppCompatActivity() {
    val TAG: String = "Register"
    var isExistBlank = false
    var isPWSame = false
    lateinit var email: EditText
    val base_url = "http://ec2-18-117-147-62.us-east-2.compute.amazonaws.com:8080/api/"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

//        email = findViewById(R.id.editText_email)

        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RegisterService::class.java)

        button_register2.setOnClickListener {
//            Log.d(TAG, "회원가입 버튼 클릭")

            val id = editText_id.text.toString()
            val pw = editText_pw.text.toString()
            val pw_re = editText_pw_re.text.toString()
            val emailStr = editText_email.text.toString()

            if(id.isEmpty() || pw.isEmpty() || pw_re.isEmpty()){
                isExistBlank = true
            }
            else{
                if(pw == pw_re)
                    isPWSame =true
            }

            // 회원가입 성공
            if(!isExistBlank && isPWSame){
                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                service.register(id, emailStr, pw).enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        val result = response.body()
                        Log.d(TAG, "${result}")
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Log.e(TAG, "${t.localizedMessage}")
                    }
                })
//                val sharedPreference = getSharedPreferences("register file", Context.MODE_PRIVATE)
//                val editor = sharedPreference.edit()
//                editor.putString("id", id)
//                editor.putString("pw", pw)
//                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                if(isExistBlank)
                    dialog("blank")
                else if(!isPWSame)
                    dialog("not same")
            }
        }
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return true
    }

    fun dialog(type: String){
        val dialog = AlertDialog.Builder(this)

        if(type.equals("blank")){
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("필수 항목을 입력하시오")
        }
        else if(type.equals("not same")){
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("비밀번호 불일치")
        }

        val dialog_listener = object: DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which){
                    DialogInterface.BUTTON_POSITIVE ->
                        Log.d(TAG, "다이얼로그")
                }
            }
        }
        dialog.setPositiveButton("확인", dialog_listener)
        dialog.show()
    }
}

