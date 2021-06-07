package com.erkuai.modularization

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.erkuai.annotation.ARouter


@ARouter("/app/LoginActivity")
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}