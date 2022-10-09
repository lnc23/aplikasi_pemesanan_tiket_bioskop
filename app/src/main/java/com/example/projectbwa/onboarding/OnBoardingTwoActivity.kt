package com.example.projectbwa.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectbwa.R
import com.example.projectbwa.Sign.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_onboarding_two.*

class OnBoardingTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_two)

        btn_home.setOnClickListener{
            var intent = Intent(this@OnBoardingTwoActivity, OnBoardingTreeActivity::class.java)
            startActivity(intent)
        }

        btn_daftar.setOnClickListener{
            finishAffinity()
            var intent = Intent(this@OnBoardingTwoActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}