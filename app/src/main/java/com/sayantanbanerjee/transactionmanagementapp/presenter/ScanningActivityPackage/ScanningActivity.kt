@file:Suppress("DEPRECATION")

package com.sayantanbanerjee.transactionmanagementapp.presenter.ScanningActivityPackage

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.integration.android.IntentIntegrator
import com.sayantanbanerjee.transactionmanagementapp.NetworkConnectivity
import com.sayantanbanerjee.transactionmanagementapp.R
import com.sayantanbanerjee.transactionmanagementapp.data.model.State
import com.sayantanbanerjee.transactionmanagementapp.data.preference.AppPreferenceHelper
import com.sayantanbanerjee.transactionmanagementapp.databinding.ActivityScanningBinding
import com.yarolegovich.lovelydialog.LovelyStandardDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// Activity to scan the QR code for verification.
// After successful scanning, the fields are displayed, and user can accept or reject the transaction.
@AndroidEntryPoint
class ScanningActivity : AppCompatActivity() {

    private var senderPhoneNumber = ""
    private var myPhoneNumber = ""
    private var amount = 0
    private var parity = 0
    private var transactionID = ""
    private var scanningDone = false

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

        // Initialize QR scanner once user presses on it.
        binding.scanQR.setOnClickListener {
            val integrator = IntentIntegrator(this)
            integrator.setOrientationLocked(false)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            integrator.setPrompt("Scan particular QR Code for verification transaction")
            integrator.setBeepEnabled(true)
            integrator.setBarcodeImageEnabled(true)
            integrator.initiateScan()
        }

        // Record the transaction as accepted and save to local database if user accepts the transaction.
        binding.acceptButton.setOnClickListener {
            if (NetworkConnectivity.isNetworkAvailable(this)) {
                var record = AppPreferenceHelper(sharedPreferences).getRecordNumber()
                record++
                AppPreferenceHelper(sharedPreferences).setRecordNumber(record)
                val curState = State(transactionID, senderPhoneNumber, "ACCEPTED")
                viewModel.saveStateToFirebase(curState)
                viewModel.saveRecord(senderPhoneNumber, amount, parity, "ACCEPTED")
                Toast.makeText(this, "Transaction Accepted and Recorded", Toast.LENGTH_LONG).show()
                super.onBackPressed()
            } else {
                Toast.makeText(this, "Network connectivity isn\'t available", Toast.LENGTH_LONG)
                    .show()
            }

        }

        // Record the transaction as rejected nd save to local database if user rejects the transaction.
        binding.rejectButton.setOnClickListener {
            if (NetworkConnectivity.isNetworkAvailable(this)) {
                var record = AppPreferenceHelper(sharedPreferences).getRecordNumber()
                record++
                AppPreferenceHelper(sharedPreferences).setRecordNumber(record)
                val curState = State(transactionID, senderPhoneNumber, "REJECTED")
                viewModel.saveStateToFirebase(curState)
                viewModel.saveRecord(senderPhoneNumber, amount, parity, "REJECTED")
                Toast.makeText(this, "Transaction Rejected and Recorded", Toast.LENGTH_LONG).show()
                super.onBackPressed()
            } else {
                Toast.makeText(this, "Network connectivity isn\'t available", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    // Getting the QR data after it is scanned.
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

    // After QR code is scanned, it is checked first.
    // Firstly, the pattern is matched to the format for a transaction QR, if not, then exit.
    // Next if the QR format is correctly to a transaction, it is checked if the transaction is meant for the person scanning the QR.
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

    // resetting views and data.
    private fun resetViewsAndData() {
        senderPhoneNumber = ""
        myPhoneNumber = ""
        amount = 0
        parity = 0
        transactionID = ""
        binding.detailsView.visibility = View.GONE
    }

    // Setting up views and data after scanning the QR.
    @SuppressLint("SetTextI18n")
    private fun setViewAndData(contentsArray: Array<String>) {

        senderPhoneNumber = contentsArray[1]
        myPhoneNumber = contentsArray[2]
        amount = contentsArray[3].toInt()
        parity = contentsArray[4].toInt()
        transactionID = contentsArray[5]

        scanningDone = true
        binding.scanQR.visibility = View.GONE
        binding.detailsView.visibility = View.VISIBLE
        binding.senderContactNumber.text = senderPhoneNumber
        binding.amountInvolved.text = "Rs. $amount"
        if (parity == 1) {
            binding.parity.text = "You Sent Money"
        } else {
            binding.parity.text = "You Received Money"
        }
    }

    // To detect the Back button, to trigger the dialog only when user already scanned the QR.
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (scanningDone) {
                showExitConfirmationDialog()
            } else {
                super.onBackPressed()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    // Dialog shown when the QR code is already scanned and user presses on the back button, to confirm User exit.
    private fun showExitConfirmationDialog() {
        LovelyStandardDialog(this, LovelyStandardDialog.ButtonLayout.VERTICAL)
            .setTopColorRes(R.color.indigo)
            .setButtonsColorRes(R.color.white)
            .setIcon(R.drawable.ic_baseline_exit_to_app_24)
            .setTitle(R.string.exit_confirmation_title)
            .setMessage(R.string.exit_confirmation_message)
            .setPositiveButton(R.string.exit_mssg,
                View.OnClickListener {
                    Toast.makeText(this, "Transaction Discarded!", Toast.LENGTH_LONG).show()
                    finish()
                })
            .setNegativeButton(R.string.cancel_mssg, View.OnClickListener {

            })
            .show()
    }
}
