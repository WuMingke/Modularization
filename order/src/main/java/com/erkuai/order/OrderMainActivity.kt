package com.erkuai.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erkuai.common.NetManagerUtils

class OrderMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_main)

        NetManagerUtils.getNetConfig()
    }
}