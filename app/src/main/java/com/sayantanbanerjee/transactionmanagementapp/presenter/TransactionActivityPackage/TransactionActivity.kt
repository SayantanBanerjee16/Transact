package com.sayantanbanerjee.transactionmanagementapp.presenter.TransactionActivityPackage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.sayantanbanerjee.transactionmanagementapp.R
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sayantanbanerjee.transactionmanagementapp.data.preference.AppPreferenceHelper
import com.sayantanbanerjee.transactionmanagementapp.databinding.ActivityTransactionBinding
import com.sayantanbanerjee.transactionmanagementapp.presenter.AddRecordActivity
import com.sayantanbanerjee.transactionmanagementapp.presenter.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TransactionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionBinding

    @Inject
    lateinit var factory: TransactionViewModelFactory

    @Inject
    lateinit var transactionAdapter: TransactionAdapter

    lateinit var viewModel: TransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction)
        viewModel = ViewModelProvider(this, factory).get(TransactionViewModel::class.java)

        initRecyclerView()

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this@TransactionActivity, AddRecordActivity::class.java)
            startActivity(intent)
        }

        viewModel.getSumSentAcceptedValue().observe(this, Observer {
            binding.acceptedSentSum.text = it.toString()
        })

        viewModel.getSumReceivedValue().observe(this, Observer {
            binding.receivedSum.text = it.toString()
        })

        viewModel.getAllRecords().observe(this, {
            transactionAdapter.differ.submitList(it)
        })

    }

    private fun initRecyclerView() {
        binding.transactionRecyclerView.adapter = transactionAdapter
        binding.transactionRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}
