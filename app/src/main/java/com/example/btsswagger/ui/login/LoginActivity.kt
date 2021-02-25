package com.example.btsswagger.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.btsswagger.data.model.LoginModel
import com.example.btsswagger.data.response.LoginResponse
import com.example.btsswagger.data.retrofit.ApiConfig
import com.example.btsswagger.ui.register.RegisterActivity
import com.example.btsswagger.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityLoginBinding
    private var username: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        activityBinding.btnLogin.setOnClickListener{
            if (isInputValid()) {
                ApiConfig.getApiService().doLogin(LoginModel(password, username))
                    .enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            val body = response.body()
                            if (response.code() == 200 && body != null) {
                                Toast.makeText(this@LoginActivity, body.message, Toast.LENGTH_SHORT)
                                    .show()
                                Log.d("Login", body.data.token)
                            } else {
                                Toast.makeText(this@LoginActivity, "Login Gagal", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Toast.makeText(this@LoginActivity, "Login Gagal", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
            }
        }

        activityBinding.btnRegister.setOnClickListener{
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun isInputValid(): Boolean {
        username = activityBinding.etUsername.text.toString().trim()
        password = activityBinding.etPassword.text.toString().trim()

        if(TextUtils.isEmpty(username)) {
            activityBinding.etUsername
                .setError("username cannot be empty")
        }

        if(TextUtils.isEmpty(password)) {
            activityBinding.etPassword
                .setError("password cannot be empty")
        }

        return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)
    }
}