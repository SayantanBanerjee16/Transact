@file:Suppress("DEPRECATION")

package com.sayantanbanerjee.transactionmanagementapp.presenter.ScanningActivityPackage

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.integration.android.IntentIntegrator
import com.sayantanbanerjee.transactionmanagementapp.R
import com.sayantanbanerjee.transactionmanagementapp.data.preference.AppPreferenceHelper
import com.sayantanbanerjee.transactionmanagementapp.databinding.ActivityScanningBinding
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

        binding.scanQR.setOnClickListener {
            val integrator = IntentIntegrator(this)
            integrator.setOrientationLocked(false)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            integrator.setPrompt("Scan particular QR Code for verification transaction")
            integrator.setBeepEnabled(true)
            integrator.setBarcodeImageEnabled(true)
            integrator.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
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
            Toast.makeText(this, "Wrong QR Code Scanned", Toast.LENGTH_LONG).show()
        } else {
            val senderPhoneNumber = contentsArray[1]
            val myPhoneNumber = contentsArray[2]
            val amount = contentsArray[3].toInt()
            val parity = contentsArray[4].toInt()
            val transactionID = contentsArray[5]
            val phoneNumberSavedInDevice =
                AppPreferenceHelper(sharedPreferences).getUserMobileNumber()
            if (phoneNumberSavedInDevice == myPhoneNumber) {
                Toast.makeText(
                    this,
                    senderPhoneNumber + "\n" + myPhoneNumber + "\n" + amount.toString()
                            + "\n" + parity.toString() + "\n" + transactionID,
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(this, "Transaction isn't meant for you!", Toast.LENGTH_LONG).show()
            }

        }

    }
}
