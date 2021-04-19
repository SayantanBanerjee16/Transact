package com.sayantanbanerjee.transactionmanagementapp.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sayantanbanerjee.transactionmanagementapp.R
import com.sayantanbanerjee.transactionmanagementapp.databinding.ActivityHomeBinding
import com.sayantanbanerjee.transactionmanagementapp.presenter.TransactionActivityPackage.TransactionActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.routeToTransactionActivity.setOnClickListener {
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent)
        }
    }
}
