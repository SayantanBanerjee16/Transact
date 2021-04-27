package com.sayantanbanerjee.transactionmanagementapp.presenter.TransactionActivityPackage

import androidx.appcompat.app.AppCompatActivity
import com.sayantanbanerjee.transactionmanagementapp.R
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sayantanbanerjee.transactionmanagementapp.databinding.ActivityTransactionBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TransactionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionBinding

    @Inject
    lateinit var factory: TransactionViewModelFactory

    lateinit var viewModel: TransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction)
        viewModel = ViewModelProvider(this, factory).get(TransactionViewModel::class.java)

        binding.floatingActionButton.setOnClickListener {
            viewModel.saveRecord()
            Toast.makeText(this, "Database Row Added", Toast.LENGTH_SHORT).show()
        }

        viewModel.getSumSentAcceptedValue().observe(this, Observer {
            binding.acceptedSentSum.text = it.toString()
        })

        viewModel.getSumReceivedValue().observe(this, Observer {
            binding.receivedSum.text = it.toString()
        })

        viewModel.getAllRecords().observe(this, {
            Log.i("######", it.toString())
        })

    }
}
