package com.sayantanbanerjee.transactionmanagementapp.presenter.AddRecordActivityPackage

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.sayantanbanerjee.transactionmanagementapp.R
import com.sayantanbanerjee.transactionmanagementapp.data.preference.AppPreferenceHelper
import com.sayantanbanerjee.transactionmanagementapp.databinding.ActivityAddRecordBinding
import com.yarolegovich.lovelydialog.LovelyStandardDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecordBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var factory: AddRecordViewModelFactory

    lateinit var viewModel: AddRecordViewModel

    private var recordAdditionDone = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_record)
        viewModel = ViewModelProvider(this, factory).get(AddRecordViewModel::class.java)

        binding.saveButton.setOnClickListener {
            val phoneNumber = binding.phoneNumber.editText?.text.toString()
            val amount = binding.amountInvolved.editText?.text.toString()
            var qrParity: Int = 0
            if (binding.parityGroup.checkedRadioButtonId == R.id.amountReceivedButton) {
                qrParity = 1
            }

            if (phoneNumber == "" || amount == "") {
                Toast.makeText(this, "Fields cannot be left empty!", Toast.LENGTH_SHORT).show()
            } else {

                afterScanningViewsSetup()

                val minePhoneNumber = AppPreferenceHelper(sharedPreferences).getUserMobileNumber()
                val recordNumber = AppPreferenceHelper(sharedPreferences).getRecordNumber()

                var inputForQREncryption = ""
                inputForQREncryption += "QRTRANSACTION"
                inputForQREncryption += ";"
                inputForQREncryption += minePhoneNumber
                inputForQREncryption += ";"
                inputForQREncryption += phoneNumber
                inputForQREncryption += ";"
                inputForQREncryption += amount
                inputForQREncryption += ";"
                inputForQREncryption += qrParity.toString()
                inputForQREncryption += ";"
                inputForQREncryption += recordNumber



                try {
                    val barcodeEncoder = BarcodeEncoder()
                    val bitmap = barcodeEncoder.encodeBitmap(
                        inputForQREncryption,
                        BarcodeFormat.QR_CODE,
                        600,
                        600
                    )
                    binding.qrCode.setImageBitmap(bitmap)
                    Toast.makeText(this, "QR Successfully generated", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Toast.makeText(
                        this,
                        "Something went wrong in generating QR Code!",
                        Toast.LENGTH_LONG
                    ).show()
                    super.onBackPressed()
                }

            }
        }

        binding.transactionSuccessful.setOnClickListener {
            val phoneNumber = binding.phoneNumber.editText?.text.toString()
            val amount = binding.amountInvolved.editText?.text.toString()
            var parity = 1
            if (binding.parityGroup.checkedRadioButtonId == R.id.amountReceivedButton) {
                parity = 0
            }
            var recordNumber = AppPreferenceHelper(sharedPreferences).getRecordNumber()
            viewModel.saveRecord(phoneNumber, amount.toInt(), parity)
            recordNumber++
            AppPreferenceHelper(sharedPreferences).setRecordNumber(recordNumber)
            Toast.makeText(this, "Transaction Saved!", Toast.LENGTH_LONG).show()
            super.onBackPressed()
        }

        binding.transactionRevoked.setOnClickListener {
            Toast.makeText(this, "Transaction Revoked!", Toast.LENGTH_LONG).show()
            super.onBackPressed()
        }
    }

    private fun afterScanningViewsSetup() {
        recordAdditionDone = true
        binding.apply {
            phoneNumber.visibility = View.INVISIBLE
            amountInvolved.visibility = View.INVISIBLE
            parityGroup.visibility = View.INVISIBLE
            saveButton.visibility = View.GONE
            transactionRevoked.visibility = View.VISIBLE
            transactionSuccessful.visibility = View.VISIBLE
            qrCode.visibility = View.VISIBLE
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (recordAdditionDone) {
                showExitConfirmationDialog()
            } else {
                super.onBackPressed()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun showExitConfirmationDialog() {
        LovelyStandardDialog(this, LovelyStandardDialog.ButtonLayout.VERTICAL)
            .setTopColorRes(R.color.indigo)
            .setButtonsColorRes(R.color.white)
            .setIcon(R.drawable.ic_baseline_exit_to_app_24)
            .setTitle(R.string.exit_confirmation_title)
            .setMessage(R.string.exit_confirmation_message)
            .setPositiveButton(R.string.exit_mssg,
                View.OnClickListener {
                    Toast.makeText(this, "Transaction Revoked!", Toast.LENGTH_LONG).show()
                    finish()
                })
            .setNegativeButton(R.string.cancel_mssg, View.OnClickListener {

            })
            .show()
    }

}
