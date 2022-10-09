package com.example.projectbwa.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectbwa.R
import com.example.projectbwa.home.HomeActivity
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_success)

        btn_tiket.setOnClickListener {
            finishAffinity()
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}