package com.erkuai.order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.erkuai.common.NetManagerUtils

class OrderMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_main)

        NetManagerUtils.getNetConfig()
    }

    fun toDebugOrder(view: View) {
//        startActivity(Intent(this, OrderDebugActivity::class.java))
    }
}