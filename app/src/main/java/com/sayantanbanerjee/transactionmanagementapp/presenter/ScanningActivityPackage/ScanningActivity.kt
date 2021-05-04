@file:Suppress("DEPRECATION")

package com.sayantanbanerjee.transactionmanagementapp.presenter.ScanningActivityPackage

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.integration.android.IntentIntegrator
import com.sayantanbanerjee.transactionmanagementapp.R
import com.sayantanbanerjee.transactionmanagementapp.data.model.State
import com.sayantanbanerjee.transactionmanagementapp.data.preference.AppPreferenceHelper
import com.sayantanbanerjee.transactionmanagementapp.databinding.ActivityScanningBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScanningActivity : AppCompatActivity() {

    private var senderPhoneNumber = ""
    private var myPhoneNumber = ""
    private var amount = 0
    private var parity = 0
    private var transactionID = ""

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

        resetViewsAndData()

        binding.scanQR.setOnClickListener {
            val integrator = IntentIntegrator(this)
            integrator.setOrientationLocked(false)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            integrator.setPrompt("Scan particular QR Code for verification transaction")
            integrator.setBeepEnabled(true)
            integrator.setBarcodeImageEnabled(true)
            integrator.initiateScan()
        }

        binding.acceptButton.setOnClickListener {
            var record = AppPreferenceHelper(sharedPreferences).getRecordNumber()
            record++
            AppPreferenceHelper(sharedPreferences).setRecordNumber(record)
            val curState = State(transactionID, senderPhoneNumber, "ACCEPTED")
            viewModel.saveStateToFirebase(curState)
            viewModel.saveRecord(senderPhoneNumber, amount, parity, "ACCEPTED")
        }

        binding.rejectButton.setOnClickListener {
            var record = AppPreferenceHelper(sharedPreferences).getRecordNumber()
            record++
            AppPreferenceHelper(sharedPreferences).setRecordNumber(record)
            val curState = State(transactionID, senderPhoneNumber, "REJECTED")
            viewModel.saveStateToFirebase(curState)
            viewModel.saveRecord(senderPhoneNumber, amount, parity, "REJECTED")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                resetViewsAndData()
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                checkCode(result.contents)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun checkCode(contents: String) {
        val contentsArray = contents.split(";").toTypedArray()
        val startCode = contentsArray[0]
        if (contentsArray.size != 6 && startCode != "QRTRANSACTION") {
            resetViewsAndData()
            Toast.makeText(this, "Wrong QR Code Scanned", Toast.LENGTH_LONG).show()
        } else {
            val phoneNumberSavedInDevice =
                AppPreferenceHelper(sharedPreferences).getUserMobileNumber()
            if (phoneNumberSavedInDevice == contentsArray[2]) {
                setViewAndData(contentsArray)
            } else {
                resetViewsAndData()
                Toast.makeText(this, "Transaction isn't meant for you!", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun resetViewsAndData() {
        senderPhoneNumber = ""
        myPhoneNumber = ""
        amount = 0
        parity = 0
        transactionID = ""
        binding.detailsView.visibility = View.GONE
    }

    @SuppressLint("SetTextI18n")
    private fun setViewAndData(contentsArray: Array<String>) {

        senderPhoneNumber = contentsArray[1]
        myPhoneNumber = contentsArray[2]
        amount = contentsArray[3].toInt()
        parity = contentsArray[4].toInt()
        transactionID = contentsArray[5]

        binding.detailsView.visibility = View.VISIBLE
        binding.senderContactNumber.text = senderPhoneNumber
        binding.amountInvolved.text = amount.toString()
        if (parity == 1) {
            binding.parity.text = "AMOUNT SENT BY YOU"
        } else {
            binding.parity.text = "AMOUNT RECEIVED TO YOU"
        }
    }
}
