package com.example.btsswagger.ui.register

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.btsswagger.data.model.RegisterModel
import com.example.btsswagger.data.response.RegisterResponse
import com.example.btsswagger.data.retrofit.ApiConfig
import com.example.btsswagger.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityRegisterBinding
    private var username: String = ""
    private var password: String = ""
    private var email: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        activityBinding.btnRegister.setOnClickListener {
            if (isInputValid()) {
                ApiConfig.getApiService().doRegister(RegisterModel(email, password, username))
                    .enqueue(object : Callback<RegisterResponse> {
                        override fun onResponse(
                            call: Call<RegisterResponse>,
                            response: Response<RegisterResponse>
                        ) {
                            val body = response.body()
                            if (response.code() == 200) {
                                Toast.makeText(this@RegisterActivity, body!!.message, Toast.LENGTH_SHORT)
                                    .show()
                                finish()
                            } else {
                                Toast.makeText(this@RegisterActivity, "Register Gagal", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                            Toast.makeText(this@RegisterActivity, "Register Gagal", Toast.LENGTH_SHORT)
                                .show()
                        }

                    })
            }
        }
    }

    fun isInputValid(): Boolean {
        username = activityBinding.etUsername.text.toString().trim()
        password = activityBinding.etPassword.text.toString().trim()
        email = activityBinding.etEmail.text.toString().trim()

        if (TextUtils.isEmpty(username)) {
            activityBinding.etUsername
                .setError("username cannot be empty")
        }

        if (TextUtils.isEmpty(password)) {
            activityBinding.etPassword
                .setError("password cannot be empty")
        }

        if (TextUtils.isEmpty(email)) {
            activityBinding.etEmail
                .setError("password cannot be empty")
        }

        return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)
                && !TextUtils.isEmpty(email)
    }
}