package com.sayantanbanerjee.transactionmanagementapp.presenter

import androidx.appcompat.app.AppCompatActivity
import com.sayantanbanerjee.transactionmanagementapp.R
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.sayantanbanerjee.transactionmanagementapp.databinding.ActivityTransactionBinding


class TransactionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction)
        binding.addTransactionToDatabase.setOnClickListener {
            Toast.makeText(this, "Database Row Added", Toast.LENGTH_SHORT).show()
        }
    }
}
