package com.example.projectbwa.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectbwa.R
import com.example.projectbwa.Sign.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_onboarding_tree.*

class OnBoardingTreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_tree)

        btn_mulai.setOnClickListener {
            finishAffinity()
            var intent = Intent(this@OnBoardingTreeActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}