package com.erkuai.modularization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.erkuai.annotation.ARouter

@ARouter("/app/MainActivity")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toOrder(view: View) {

    }

    fun toPersonal(view: View) {

    }

}