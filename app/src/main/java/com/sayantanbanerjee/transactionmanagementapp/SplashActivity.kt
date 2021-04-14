package com.sayantanbanerjee.transactionmanagementapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sayantanbanerjee.transactionmanagementapp.databinding.ActivitySplashBinding

// Initial screen which is displayed in the application.
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        supportActionBar?.hide()
        binding.splashImageView.animate().alphaBy(1f).duration = 1500
        delayFor1500msAndSwitchToAuthActivity()
    }

    // THe splash image is displayed for 1.5 seconds.
    // So delay until its completely animated and then switch to the [AuthActivity.kt].
    private fun delayFor1500msAndSwitchToAuthActivity() {
        object : CountDownTimer(1500, 1000) {
            override fun onTick(l: Long) {}
            override fun onFinish() {
                val intent: Intent = Intent(this@SplashActivity, AuthActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }
}
