package com.sayantanbanerjee.transactionmanagementapp.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sayantanbanerjee.transactionmanagementapp.R
import com.sayantanbanerjee.transactionmanagementapp.databinding.ActivityHomeBinding
import com.sayantanbanerjee.transactionmanagementapp.presenter.AddRecordActivityPackage.AddRecordActivity
import com.sayantanbanerjee.transactionmanagementapp.presenter.ScanningActivityPackage.ScanningActivity
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

        binding.routeToScanningActivity.setOnClickListener {
            val intent = Intent(this, ScanningActivity::class.java)
            startActivity(intent)
        }

        binding.routeToAddRecordActivity.setOnClickListener {
            val intent = Intent(this, AddRecordActivity::class.java)
            startActivity(intent)
        }
    }
}
