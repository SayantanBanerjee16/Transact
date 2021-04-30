package com.sayantanbanerjee.transactionmanagementapp.presenter.ScanningActivityPackage

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sayantanbanerjee.transactionmanagementapp.R
import com.sayantanbanerjee.transactionmanagementapp.databinding.ActivityAddRecordBinding
import com.sayantanbanerjee.transactionmanagementapp.databinding.ActivityScanningBinding
import com.sayantanbanerjee.transactionmanagementapp.presenter.AddRecordActivityPackage.AddRecordViewModel
import com.sayantanbanerjee.transactionmanagementapp.presenter.AddRecordActivityPackage.AddRecordViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScanningActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanningBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var factory: ScanningViewModelFactory

    lateinit var viewModel: ScanningViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scanning)
        viewModel = ViewModelProvider(this, factory).get(ScanningViewModel::class.java)

    }
}