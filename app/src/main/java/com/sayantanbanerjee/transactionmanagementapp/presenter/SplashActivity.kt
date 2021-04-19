package com.sayantanbanerjee.transactionmanagementapp.presenter

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sayantanbanerjee.transactionmanagementapp.R
import com.sayantanbanerjee.transactionmanagementapp.data.preference.AppPreferenceHelper
import com.sayantanbanerjee.transactionmanagementapp.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


// Initial screen which is displayed in the application.

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    @Inject
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        supportActionBar?.hide()
        binding.splashImageView.animate().alphaBy(1f).duration = 1500
        delayFor1500msAndSwitchToAuthActivity()
    }

    // THe splash image is displayed for 1.5 seconds.
    // So delay until its completely animated.
    // If user is not authenticated, re-direct him to the [AuthActivity.kt].
    // Else re-direct him to the [HomeActivity.kt].
    private fun delayFor1500msAndSwitchToAuthActivity() {
        object : CountDownTimer(1500, 1000) {
            override fun onTick(l: Long) {}
            override fun onFinish() {
                val intent = if (AppPreferenceHelper(sharedPreferences).isUserAuthenticated()) {
                    Intent(this@SplashActivity, HomeActivity::class.java)
                } else {
                    Intent(this@SplashActivity, AuthActivity::class.java)
                }
                startActivity(intent)
                finish()
            }
        }.start()
    }
}
